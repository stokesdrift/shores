package org.stokesdrift.shores;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.stokesdrift.shores.commands.CreateCommand;
import org.stokesdrift.shores.commands.SetupCommand;
import org.stokesdrift.shores.commands.ShoreCommand;
import org.stokesdrift.shores.util.ExceptionUtil;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import io.airlift.airline.Cli;
import io.airlift.airline.Cli.CliBuilder;
import io.airlift.airline.Command;
import io.airlift.airline.Help;

public class Shores implements Runnable {

	private String[] args;
	
	public static void main(String[] args) {
		Shores shores = new Shores(args);
		shores.run();

		// 2. find template directory
		// 3. load templates based on generator definition? 
				// - file naming convention with model references pointing to template
				// - output pathing?
				// - support for system / command line options
				// - general build / project configuration
		// 4. create directories based on generator definition
		// 5. create files based on iterating over the models and using generator definition as a base
			// - support generating based on new model name, make sure not to override if already exists
	}
	
	
	public Shores(String[] args) {
		this.args = args;
	}
	
	public Cli<Runnable> createRunner() throws IOException {
		String workingDir = System.getProperty("user.dir");
		System.out.println("Running from: " + workingDir);
		
		
		Package pkg = ShoreCommand.class.getPackage();
		ClassPath path = ClassPath.from(this.getClass().getClassLoader());
		ImmutableSet<ClassInfo> classes = path.getTopLevelClasses(pkg.getName());
		
		List<Class<? extends Runnable>> commandClasses = classes.stream().filter(classInfo -> {
			Class cls = convertToClass(classInfo);
			if (isCommandClass(cls)) {
				return true;
			}
			return false;					
		}).map(c -> convertToClass(c)).collect(Collectors.toList());
		
		
		
		CliBuilder<Runnable> builder = Cli.<Runnable>builder(this.getClass().getName().toLowerCase())
			.withDescription("Code generator")
			.withDefaultCommand(Help.class)
			.withCommands(commandClasses);
				
		return builder.build();
	}
	
	
	public void run() {
		Cli<Runnable> cli = null;
		try {
			cli = this.createRunner();
		} catch (IOException e) {
			throw ExceptionUtil.unchecked(e);
		}
		Runnable runnable = cli.parse(args);
		
		Map<String,String> options = new HashMap<String,String>();
		// TODO add options from command line
		
		if(runnable instanceof ShoreCommand) {
			ShoreCommand command = (ShoreCommand)runnable;
			
			command.setup(options);
		}		
		runnable.run();	
	}
	
	
	protected boolean isCommandClass(Class cls) {
		return (!Modifier.isAbstract(cls.getModifiers()) && !Modifier.isInterface(cls.getModifiers()) && cls.isAnnotationPresent(Command.class));
	}
	
	protected Class convertToClass(ClassInfo ci) {
		String className = ci.getName();
		Class cls = null;
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw ExceptionUtil.unchecked(e);
		}
		return cls;
	}
			
}

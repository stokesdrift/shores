# Overview

Shores is a generation framework for you to make waves. 



## How to use 

*Templates:*
Template language is based on freemarker.

*Models:*
Models are based on the open api specifications: [https://www.openapis.org/specification/repo]


### App Configuration 
Configuration is same format as the (heroku app.json)[https://devcenter.heroku.com/articles/app-json-schema] but it adds generator definitions:

```
 "dunes": [
     { 
        "name": "docker",
        "location": "./docker-generator",
        "overrides":{
           "config-key1":"overridden value 1"
        }
     }
  ]
 ```
 
 By default this file is looked at in the root of where the command is run.

Directory structure of an app:
* `app.json` - Application / Service configuration, can be setup running `shores setup`
* `dunes/` - Directory containing generators
* `generated/` - Directory containing the generated code 
* `overrides/` - Directory containing specific overrides on existing generators (useful for project specific template changes)
* `models/` - Directory containing the model definition files


### Create a Sand Dune (aka Generator)

Generator definition is based on yeoman generators with a bit of boilr mixed in. (boilr project.json)[https://github.com/tmrts/boilr-license/blob/master/project.json] setup defines the interactions with the user for values the generator needs setup. Yeoman generator config is for general setup definition.

Directory structure of a sand dune:
* `generator-definition.json` - Configuration definition file for a generator
* `project.json` - Boilr like key value override options with defaults
* `templates/` - Template files used by the generator

## TODO

* Process openapi spec files
* Use Moka for configuration
* Add gradle plugin
* Leverage ideas from boilr
** [https://github.com/tmrts/boilr/wiki/Templates]
** [https://github.com/tmrts/boilr/wiki/Creating-Templates]
* Distribute via brew 
** Example tap [https://github.com/srcclr/homebrew-srcclr/blob/master/srcclr.rb]
** example article [https://www.sourceclear.com/blog/distribute-your-java-app-via-brew/]
** homebrew how too [http://kylebebak.github.io/post/distribute-program-via-homebrew]


Notes:
* Airline uses guava, so use that for some of the IOC i'd need
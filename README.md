# Nami Template Plugin

## ZERO TRUST
Keep in mind, plugins are unsandboxed, never launch any plugins you dont trust, you cant read its source! It is highly recommended to use only plugins you made yourself, or ones published on github!

<img width="1920" height="1080" alt="ClickGUI" src="assets/nami-plugin-fun.png" />

### API
Join our discord (https://discord.gg/auHTtNAqRq) to get news about api and plugin development
API is currently still in development, it is expected if many things gonna change

### How to build
1. You need PAT token configured in your root .grale/gradle.properties to access nami-api dependency
2. Then you can just build it like normal client
3. ./gradlew build

### How to launch
1. Once its builded, you need nami-api and nami-client modules with correct version in your mod folder
2. nami-client normally already has nami-api inside of it
3. You can also launch it without nami-client, if any of the mods hands the configuration for services to nami-api, like here https://github.com/NamiDevelopment/nami/blob/mc.1.21.11/nami-client/src/main/java/namidevelopment/kiriyaga/nami/contract/ClientFeatureContracts.java

### How to do N feature?
1. You can look into nami-client features code, it has every case of api use, for examples, or pasting https://github.com/NamiDevelopment/nami/tree/mc.1.21.11/nami-client
2. You can ask other plugin developers, or dev team, for help or explanation in our discord https://discord.gg/auHTtNAqRq
3. You can read the API source code here https://github.com/NamiDevelopment/nami/tree/mc.1.21.11/nami-api

# fxgen (Go)

Easily build and deploy [Go functions](https://github.com/GoogleCloudPlatform/cloud-functions-go) to [Google Cloud Functions](https://cloud.google.com/functions/) using Swagger to define routes and models.

## Getting Started

### Prerequisites:

* Make
* Docker

### Overview

1. Write your `swagger.yaml` file. You'll need to define a [unique tag](https://swagger.io/docs/specification/2-0/grouping-operations-with-tags/) per route. Check out the [modified petstore example](./example/petstore.json) in the examples directory. 
2. Follow the guide below for "Building + Generating"
3. Run Make in the generated Client directory to build the functions.
4. Upload the `function.zip` located in the `output/fx/*` directory.

## Building + Generating

### Compiling the Codegen Jar

Download `swagger-codegen` and compile it. Make sure it is one directory out of this one when running the following command.

```bash
ls -la ../swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar
```

After compiling `swagger-codegen`, compile this project and generate a sample `fxgen` server library.
The example below uses a modified 

```
mvn package
```

### Generating a `fxgen` client

In your generator project.  A single jar file will be produced in `target`.  You can now use that with codegen:

```
java -cp target/fxgen-swagger-codegen-1.0.0.jar:../swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar io.swagger.codegen.SwaggerCodegen generate -l fxgen -i example/petstore.json -o target/myClient
```

Now your templates are available to the client generator and you can write output values

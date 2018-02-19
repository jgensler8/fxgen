package com.vastorchard.fxgen;

import io.swagger.codegen.*;
import io.swagger.codegen.languages.AbstractGoCodegen;
import io.swagger.codegen.languages.GoClientCodegen;
import io.swagger.models.properties.*;

import java.util.*;
import java.io.File;

public class FxgenGenerator extends AbstractGoCodegen implements CodegenConfig {

    // source folder where to write the files
    protected String sourceFolder = "src";
    protected String apiVersion = "1.0.0";

    /**
     * Configures the type of generator.
     *
     * @return the CodegenType for this generator
     * @see io.swagger.codegen.CodegenType
     */
    public CodegenType getTag() {
        return CodegenType.SERVER;
    }

    /**
     * Configures a friendly name for the generator.  This will be used by the generator
     * to select the library with the -l flag.
     *
     * @return the friendly name for the generator
     */
    public String getName() {
        return "fxgen";
    }

    /**
     * Returns human-friendly help for the generator.  Provide the consumer with help
     * tips, parameters here
     *
     * @return A string value for the help message
     */
    public String getHelp() {
        return "Generates a fxgen client library.";
    }

    public void configure() {
        modelTemplateFiles.clear();
        apiTemplateFiles.clear();
        supportingFiles.clear();

        apiDocTemplateFiles.clear();
        apiTestTemplateFiles.clear();
        modelDocTemplateFiles.clear();
        modelTestTemplateFiles.clear();

        // set the output folder here
        outputFolder = "generated-code/fxgen";

        /**
         * Models.  You can write model files using the modelTemplateFiles map.
         * if you want to create one template for file, you can do so here.
         * for multiple files for model, just put another entry in the `modelTemplateFiles` with
         * a different extension
         */
        modelTemplateFiles.put(
                "model.mustache", // the template to use
                ".go");       // the extension for each file to write

        /**
         * Api classes.  You can write classes for each Api file with the apiTemplateFiles map.
         * as with models, add multiple entries with different extensions for multiple files per
         * class
         */
        apiTemplateFiles.put("api.mustache", "-pkg.go");
        apiTemplateFiles.put("main.mustache", "-wrap.go");

        /**
         * Template Location.  This is the location which templates will be read from.  The generator
         * will use the resource stream to attempt to read the templates.
         */
        templateDir = "fxgen";

        /**
         * Api Package.  Optional, if needed, this can be used in templates
         */
        apiPackage = "github.com/jgensler8/testfunction";

        /**
         * Model Package.  Optional, if needed, this can be used in templates
         */
        modelPackage = "github.com/jgensler8/testfunction/models";

        /**
         * Additional Properties.  These values can be passed to the templates and
         * are available in models, apis, and supporting files
         */
        additionalProperties.put("apiVersion", apiVersion);

        /**
         * Supporting Files.  You can write single files for the generator with the
         * entire object tree available.  If the input file has a suffix of `.mustache
         * it will be processed by the template engine.  Otherwise, it will be copied
         */
        supportingFiles.add(new SupportingFile("Makefile.mustache", "", "Makefile"));
        supportingFiles.add(new SupportingFile("package.json", "", "package.json"));
        supportingFiles.add(new SupportingFile("index.js", "", "index.js"));
        supportingFiles.add(new SupportingFile("glide.yaml", "", "glide.yaml"));
        // execer
        supportingFiles.add(new SupportingFile("local_modules/execer/binding.gyp", "local_modules/execer", "binding.gyp"));
        supportingFiles.add(new SupportingFile("local_modules/execer/execer.cc", "local_modules/execer", "execer.cc"));
        supportingFiles.add(new SupportingFile("local_modules/execer/index.js", "local_modules/execer", "index.js"));
        supportingFiles.add(new SupportingFile("local_modules/execer/package.json", "local_modules/execer", "package.json"));
    }

    public FxgenGenerator() {
        super();

        configure();
    }

    /**
     * Escapes a reserved word as defined in the `reservedWords` array. Handle escaping
     * those terms here.  This logic is only called if a variable matches the reserved words
     *
     * @return the escaped term
     */
    @Override
    public String escapeReservedWord(String name) {
        return "_" + name;  // add an underscore to the name
    }

    /**
     * Location to write model files.  You can use the modelPackage() as defined when the class is
     * instantiated
     */
    public String modelFileFolder() {
        return outputFolder + "/" + "models";
    }

    /**
     * Location to write api files.  You can use the apiPackage() as defined when the class is
     * instantiated
     */
    @Override
    public String apiFileFolder() {
        return outputFolder;
    }
}
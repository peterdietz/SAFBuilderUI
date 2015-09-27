package controllers;

import play.*;
import play.mvc.*;

import safbuilder.SAFPackage;
import views.html.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Application extends Controller {

    public Result index() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        SAFPackage safPackage = new SAFPackage();
        //if -m -c
        // safPackageInstance1.generateManifest(commandLine.getOptionValue('c'));

        //if -c
        //if -s
        //safPackageInstance1.setSymbolicLink(true);

        String csvPath = "/Users/peterdietz/Projects/SAFBuilder/src/sample_data/AAA_batch-metadata.csv";
        Boolean compress = false;
        try {
            safPackage.processMetaPack(csvPath, compress);
        } catch (IOException e) {
            return internalServerError(e.getMessage());
        }

        return ok(index.render("Your new application is ready.", baos.toString()));
    }

}

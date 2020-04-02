package works.ss.dynamic.pwa.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import works.ss.dynamic.pwa.backend.service.DataGenerator;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin()
@RequestMapping(value = "api/v1.0/dummy")
public class DummyDataGenerator {

    @Autowired
    private DataGenerator dataGenerator;

    @RequestMapping(value = "/{categoryCount}/{productCount}", method = RequestMethod.GET)
    public ResponseEntity<String> downloadDocument(HttpServletRequest request,
                                                           @PathVariable(value = "productCount", required = false) int productCount,
                                                           @PathVariable(value = "categoryCount", required = true) int categoryCount) throws Exception {
        dataGenerator.initDummyData(categoryCount, productCount);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

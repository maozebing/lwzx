package gsunis.bigdata.api.demo;


import gsunis.bigdata.common.dbentity.demo.TestEntity;
import gsunis.bigdata.service.demo.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 描述 ：
 *
 * @author : maozebing
 * @version : v1.00
 * @CreationDate : 2017/2/23 14:49
 * @Description :
 * @update : 修改人，修改时间，修改内容
 * @see :[相关类/方法]
 */
@Component
@Path("test")
public class TestAPI {

    @Autowired
    public TestService testService;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity getTest(@PathParam("id") long id) {
        return ResponseEntity.ok(testService.getTest(id));
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity getTests() {
        return ResponseEntity.ok(testService.listTest());
    }

    @Path("/list/find")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity getTestByName(@QueryParam(value = "name") String name) {
        return ResponseEntity.ok(testService.findByName(name));
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity addTest(TestEntity test){
        testService.saveTest(test);
        return ResponseEntity.ok().build();
    }

    @Path("/update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity updateTest(TestEntity test){
        testService.updateTest(test);
        return ResponseEntity.ok().build();
    }

    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity deleteTest(@PathParam("id") long id){
        testService.removeTest(id);
        return ResponseEntity.ok().build();
    }

    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity login(@RequestBody TestEntity test){
        testService.saveTest(test);
        return ResponseEntity.ok().build();
    }
}

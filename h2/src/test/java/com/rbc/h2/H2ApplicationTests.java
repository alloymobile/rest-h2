package com.rbc.h2;

import com.rbc.h2.service.impl.department.DepartmentService;
import com.rbc.h2.service.impl.department.dto.DepartmentDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class H2ApplicationTests {

    @LocalServerPort
    int randomServerPort;


    @Autowired
    DepartmentService departmentService;

    @Test
    public void testGetDepartmentListSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/department";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testGetDepartmentByIdSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/department/1";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("finance"));
    }

    @Test
    public void testCreateDepartmentSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        final String baseUrl = "http://localhost:" + randomServerPort + "/department";
        URI uri = new URI(baseUrl);

        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("Production");

        HttpEntity<DepartmentDTO> requestEntity = new HttpEntity<>(dto,httpHeaders);

        ResponseEntity<DepartmentDTO> result = restTemplate.postForEntity(uri,requestEntity, DepartmentDTO.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().getName().equalsIgnoreCase("Production"));
    }

    @Test
    public void testUpdateDepartmentByIdSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        final String baseUrl = "http://localhost:" + randomServerPort + "/department/2";
        URI uri = new URI(baseUrl);

        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("Production");

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "2");

        restTemplate.put(baseUrl,dto,params);

        Optional<ResponseEntity<DepartmentDTO>> department = this.departmentService.readDepartmentById(2L);

//        System.out.println("Status Code: " + result.getStatusCode());
//        System.out.println("Id: " + result.getBody().getEmpId());
//        System.out.println("Location: " + result.getHeaders().getLocation());

        Assert.assertEquals("Production", department.get().getBody().getName());
    }

    @Test
    public void testDeleteDepartmentByIdSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/department/3";
        URI uri = new URI(baseUrl);

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "3");

        restTemplate.delete(baseUrl,params);
    }

}

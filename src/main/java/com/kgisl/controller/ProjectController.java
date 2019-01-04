package com.kgisl.controller;

import java.util.List;


import com.kgisl.entity.Project;
import com.kgisl.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

/**
 * ProjectController
 */
// @CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*")
// @CrossOrigin(origins = "http://localhost:4200/")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping(value="/",headers="Accept=application/json")
    public ResponseEntity<Void> createProject(@RequestBody Project project, UriComponentsBuilder ucBuilder){
        System.out.println("Creating project "+project.getProjectname());
        projectService.createProject(project);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(project.getProjectid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<List<Project>> all() {
        System.out.println("GET ALL CALLED");
        return new ResponseEntity<>(projectService.getProjects(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProjectById(@PathVariable("id") long id) {
        System.out.println("Fetching Project with id " + id);
        Project project = projectService.findByProjectId(id);
        if (project == null) {
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", headers="Accept=application/json")
    public ResponseEntity<String> updateProject(@PathVariable("id") long id,@RequestBody Project currentProject)
    {
        // Project project = projectService.findByProjectId(currentProject.findByProjectId(id));
        // if (project==null) {
        //     return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        // }
        // project.setProjectname(currentProject.getProjectname());
        projectService.updateProject(id,currentProject);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") Long id){
        Project user = projectService.findByProjectId(id);
        if (user == null) {
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
        }
        projectService.deleteProjectById(id);
        return new ResponseEntity<Project>(HttpStatus.NO_CONTENT);
    }
}
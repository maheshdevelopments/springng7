package com.kgisl.controller;

import java.util.List;


import com.kgisl.entity.Team;
import com.kgisl.service.TeamService;

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
 * TeamController
 */
// @CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*")
// @CrossOrigin(origins = "http://localhost:4200/")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping(value="/",headers="Accept=application/json")
    public ResponseEntity<Void> createTeam(@RequestBody Team team, UriComponentsBuilder ucBuilder){
        System.out.println("Creating team "+team.getTeamname());
        teamService.createTeam(team);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(team.getTeamid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<List<Team>> all() {
        System.out.println("GET ALL CALLED");
        return new ResponseEntity<>(teamService.getTeams(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> getTeamById(@PathVariable("id") long id) {
        System.out.println("Fetching Team with id " + id);
        Team team = teamService.findByTeamId(id);
        if (team == null) {
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", headers="Accept=application/json")
    public ResponseEntity<String> updateTeam(@PathVariable("id") long id,@RequestBody Team currentTeam)
    {
        // Team team = teamService.findByTeamId(currentTeam.findByTeamId(id));
        // if (team==null) {
        //     return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        // }
        // team.setTeamname(currentTeam.getTeamname());
        teamService.updateTeam(id,currentTeam);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<Team> deleteTeam(@PathVariable("id") Long id){
        Team user = teamService.findByTeamId(id);
        if (user == null) {
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
        teamService.deleteTeamById(id);
        return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
    }
}
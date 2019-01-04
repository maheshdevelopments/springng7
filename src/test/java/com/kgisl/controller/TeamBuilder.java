package com.kgisl.controller;

import com.kgisl.entity.Team;

/**
 * TeamBuilder
 */
public class TeamBuilder {

    private Team team = new Team();

    public TeamBuilder id(Long id) {
        team.setTeamid(id);
        return this;
    }

    public TeamBuilder name(String name) {
        team.setTeamname(name);
        return this;
    }

    public Team build() {
        return team;
    }
}
package com.kgisl.service;

import java.util.List;

import javax.transaction.Transactional;

import com.kgisl.entity.Project;
import com.kgisl.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProjectServiceImp implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getProjects() {
        return (List<Project>) projectRepository.findAll();
    }

    public Project findByProjectId(Long id) {
        return projectRepository.findById(id).get();
    }

    public Project updateProject(Long id,Project project) {
        Project t = projectRepository.getOne(id);
        t.setProjectname(project.getProjectname());
        return projectRepository.save(t);
    }

    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }

}

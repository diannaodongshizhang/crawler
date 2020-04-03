package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.JobInfo;


public interface JobInfoDao extends JpaRepository<JobInfo,Long>{

}

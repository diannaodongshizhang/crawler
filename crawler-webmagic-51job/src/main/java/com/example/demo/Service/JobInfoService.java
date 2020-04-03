package com.example.demo.Service;

import java.util.List;

import com.example.demo.pojo.JobInfo;

public interface JobInfoService {
	public void save(JobInfo jobInfo);
	
	public List<JobInfo> findJobInfo(JobInfo jobInfo);
}

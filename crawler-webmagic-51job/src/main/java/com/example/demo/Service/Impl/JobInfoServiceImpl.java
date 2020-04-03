package com.example.demo.Service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import org.springframework.stereotype.Service;

import com.example.demo.Service.JobInfoService;
import com.example.demo.dao.JobInfoDao;
import com.example.demo.pojo.JobInfo;

@Service
public class JobInfoServiceImpl implements JobInfoService{
	
	
	@Autowired
	private JobInfoDao jobInfoDao;
	@Override
	@Transactional
	public void save(JobInfo jobInfo) {
		JobInfo param =new JobInfo();
		param.setUrl(jobInfo.getUrl());
		param.setTime(jobInfo.getTime());
		
		List<JobInfo> list=this.findJobInfo(param);
		
		if(list.size()==0) {
			this.jobInfoDao.saveAndFlush(jobInfo);
		}
	}

	@Override
	public List<JobInfo> findJobInfo(JobInfo jobInfo) {
		
		Example example = Example.of(jobInfo);
		
		List list =this.jobInfoDao.findAll(example);
		
		return list;
	}

}

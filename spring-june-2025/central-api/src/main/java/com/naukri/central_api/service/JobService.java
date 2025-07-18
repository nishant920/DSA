package com.naukri.central_api.service;

import com.naukri.central_api.connectors.DatabaseApiConnector;
import com.naukri.central_api.dto.JobSearchFilterDto;
import com.naukri.central_api.models.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {


    DatabaseApiConnector databaseApiConnector;

    public JobService(DatabaseApiConnector databaseApiConnector){
        this.databaseApiConnector = databaseApiConnector;
    }

    public List<Job> searchJobs(JobSearchFilterDto jobSearchFilterDto){
       String query = this.createJobSearchQuery(jobSearchFilterDto);
       return this.getByQuery(query);
    }

    public String getJoinedString(List<String> strs){
        String ans = "";

        if(strs.size() == 0){
            return "''";
        }
        for(int i = 0; i < strs.size(); i++){
            if(i != strs.size() - 1){
                ans += "'"+ strs.get(i) + "'" + " , ";
            }else{
                ans += "'"+ strs.get(i) + "'";
            }
        }

        return ans;
    }


    public String createJobSearchQuery(JobSearchFilterDto jobSearchFilterDto){
        String conSkill = this.getJoinedString(jobSearchFilterDto.getJobSkill());
        String conCompany = this.getJoinedString(jobSearchFilterDto.getCompanyName());
        String conLocation = this.getJoinedString(jobSearchFilterDto.getCompanyName());
        String conTitle = this.getJoinedString(jobSearchFilterDto.getJobTitle());
        String query = String.format("SELECT * FROM job j INNER JOIN company c ON j.company_id = c.id INNER JOIN job_skills js ON js.job_id = j.id INNER JOIN skill s on js.skills_id = s.id where s.name in (%s) or j.location in (%s) or c.company_name in (%s) or j.short_description in (%s);",
               conSkill,
                conLocation,
                conCompany,
                conTitle);
        return query;
    }

    public List<Job> getByQuery(String query){
        // Database api connector
    }

    public Job saveJob(Job job){
        return databaseApiConnector.callSaveJobEndpoint(job);
    }
}

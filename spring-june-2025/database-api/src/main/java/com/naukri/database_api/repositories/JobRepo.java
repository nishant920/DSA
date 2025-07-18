package com.naukri.database_api.repositories;

import com.naukri.database_api.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepo extends JpaRepository<Job, UUID> {

    @Query(value = """
    SELECT DISTINCT j.id FROM job j 
    INNER JOIN company c ON j.company_id = c.id  
    INNER JOIN job_skills js ON js.job_id = j.id 
    INNER JOIN skill s on js.skills_id = s.id 
    WHERE s.name IN :skills
       OR j.location IN :locations
       OR c.company_name IN :companies
       OR j.short_description IN :titles
""", nativeQuery = true)
    public Object getByQuery(@Param("skills") List<String> skills,
                                 @Param("locations") List<String> locations,
                                 @Param("companies") List<String> companies,
                                 @Param("titles") List<String> titles);
}

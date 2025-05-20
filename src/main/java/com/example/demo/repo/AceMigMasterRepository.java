package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.AceMigMaster;

public interface AceMigMasterRepository extends JpaRepository<AceMigMaster, Long>{
	
	@Query("SELECT amm FROM AceMigMaster amm WHERE amm.procIndicator = :procIndicatorVal")
	List<AceMigMaster> findByProcIndicator(@Param("procIndicatorVal") String procIndicator);

}

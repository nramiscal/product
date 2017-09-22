package com.nramiscal.loginReg.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nramiscal.loginReg.models.Package;

@Repository
public interface PackageRepo extends CrudRepository<Package, Long>{

	

}
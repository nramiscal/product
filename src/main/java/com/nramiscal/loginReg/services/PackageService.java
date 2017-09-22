package com.nramiscal.loginReg.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nramiscal.loginReg.models.Package;
import com.nramiscal.loginReg.repositories.PackageRepo;

@Service
public class PackageService {
	
	private PackageRepo pkgRepo;
	
	public PackageService(PackageRepo pkgRepo) {
		this.pkgRepo = pkgRepo;
	}
	
	public void addPackage(Package pkg) {
		pkgRepo.save(pkg);
	}
	
	public List<Package> all(){
		return (List<Package>) pkgRepo.findAll();
	}

	public Package findPackageById(Long id) {
		// TODO Auto-generated method stub
		return pkgRepo.findOne(id);
	}

	public void activate(Package pkg) {
		// TODO Auto-generated method stub
		pkg.setAvailable(1);
	}
	

}

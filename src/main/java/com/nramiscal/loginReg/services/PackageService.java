package com.nramiscal.loginReg.services;

import java.util.Iterator;
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
		return pkgRepo.findOne(id);
	}

	public void activate(Package pkg) {
		pkg.setAvailability("available");
		pkgRepo.save(pkg);
	}
	
	public void deactivate(Package pkg) {
		pkg.setAvailability("unavailable");
		pkgRepo.save(pkg);
	}

	public void deletePackageById(Long id) {
		pkgRepo.delete(id);	
	}
	
	// get available packages only
	public List<Package> findAvailablePackages() { 
		List<Package> subPackages = (List<Package>) pkgRepo.findAll(); 
		Iterator<Package> i = subPackages.iterator();
		while (i.hasNext()) {
		   Package p = i.next();
		  if (!p.checkIfAvailable()) {
		    i.remove();
		  }
		}
		return subPackages;
	}	

}

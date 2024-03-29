package com.example.demo;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.bean.EvenementBean;
import com.example.demo.bean.OutilBean;
import com.example.demo.bean.PublicationBean;
import com.example.demo.dao.EnseignantChercheurRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.MembreRepository;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.proxies.EvenementProxyService;
import com.example.demo.proxies.OutilProxyService;
import com.example.demo.proxies.PublicationProxyService;
import com.example.demo.service.IMembreService;

import lombok.AllArgsConstructor;

@SpringBootApplication
@ComponentScan
@AllArgsConstructor
@EnableDiscoveryClient
@EnableFeignClients
public class MembreServiceApplication implements CommandLineRunner {
	
	EtudiantRepository etudiantRepository;
	EnseignantChercheurRepository enseignantChercheurRepository;
	MembreRepository membreRepository;
	IMembreService iMembreService;

	public static void main(String[] args) {
		SpringApplication.run(MembreServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		EnseignantChercheur ens1 =EnseignantChercheur.builder()
				.cin("888888")
				.dateNaissance(new Date())
				.email("ens1@gmail.com")
				.password("pass33")
				.nom("Mariem")
				.prenom("Marrekchi)")
				.grade("Prof")
				.etablissement("ENIS")
				.build();
		
		EnseignantChercheur ens2 =EnseignantChercheur.builder()
				.cin("666666")
				.dateNaissance(new Date())
				.email("ens2@gmail.com")
				.password("pass22")
				.nom("Mariem")
				.prenom("Ahmed)")
				.grade("Prof")
				.etablissement("ENIS")
				.build();
		
		enseignantChercheurRepository.save(ens2);
		

		Etudiant etd1 =Etudiant.builder()
		.cin("123456")
		.dateInscription(new Date())
		.dateNaissance(new Date())
		.diplome("mastère")
		.email("etd1@gmail.com")
		.password("pass1")
		.encadrant(null)
		.nom("abid")
		.prenom("youssef)")
		.sujet("blockhain")
		.build();
		
		Etudiant etd2 =Etudiant.builder()
				.cin("666666")
				.dateInscription(new Date())
				.dateNaissance(new Date())
				.diplome("mastère")
				.email("etd2@gmail.com")
				.password("pass2")
				.encadrant(null)
				.nom("ahmed")
				.prenom("mohamed)")
				.sujet("dev")
				.build();
		
		
		
		etudiantRepository.save(etd1);
		etudiantRepository.save(etd2);
		enseignantChercheurRepository.save(ens1);
		enseignantChercheurRepository.save(ens2);

		
		
		iMembreService.addEnsToEtudiant(etd1.getId(), ens1.getId());
		iMembreService.addEnsToEtudiant(etd2.getId(), ens1.getId());

		
		List<Etudiant> etds = iMembreService.getEtudiantsByEns(ens1.getId());
		for(Etudiant i:etds) {
			System.out.println(i.getNom());
		
		}
		
		
	
	}
	
	

}

package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.bean.EvenementBean;
import com.example.demo.bean.OutilBean;
import com.example.demo.bean.PublicationBean;
import com.example.demo.dao.EnseignantChercheurRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.MembreEvenementRepository;
import com.example.demo.dao.MembreOutilRepository;
import com.example.demo.dao.MembrePubRepository;
import com.example.demo.dao.MembreRepository;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.entities.Membre_Evenement;
import com.example.demo.entities.Membre_Evenement_Id;
import com.example.demo.entities.Membre_Outil;
import com.example.demo.entities.Membre_Outil_Id;
import com.example.demo.entities.Membre_Pub_Id;
import com.example.demo.entities.Membre_Publication;
import com.example.demo.proxies.EvenementProxyService;
import com.example.demo.proxies.OutilProxyService;
import com.example.demo.proxies.PublicationProxyService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class MembreService implements IMembreService {

	
	MembreRepository memberRepository;
	EtudiantRepository etudiantRepository;
	EnseignantChercheurRepository enseignantChercheurRepository;
	
	
	public Etudiant addEnsToEtudiant(Long idEtudiant, Long idEnseignant) {
		EnseignantChercheur ens = enseignantChercheurRepository.findById(idEnseignant).get();
		Etudiant etd = etudiantRepository.findById(idEtudiant).get();
		
		etd.setEncadrant(ens);
		return memberRepository.save(etd);
	}

	public Membre addMember(Membre m) {
		
		memberRepository.save(m);
		return m;
		}

	public void deleteMember(Long id) {
		memberRepository.deleteById(id);
		}
	
	public Membre updateMember(Membre m) {
		return memberRepository.saveAndFlush(m);
		}

	@Override
	public Membre findMember(Long id) {
		return memberRepository.findById(id).isPresent() ? memberRepository.findById(id).get() : null;
	}

	@Override
	public List<Membre> findAll() {
		return memberRepository.findAll();
	}

	@Override
	public Membre findByCin(String cin) {
		return memberRepository.findByCin(cin);
	}

	@Override
	public Membre findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	@Override
	public List<Membre> findByNom(String nom) {
		return memberRepository.findByNomStartingWith(nom);
	}

	@Override
	public List<Etudiant> findByDiplome(String diplome) {
		return etudiantRepository.findByDiplome(diplome);
	}

	@Override
	public List<EnseignantChercheur> findByGrade(String grade) {
		return enseignantChercheurRepository.findByGrade(grade);
	}

	@Override
	public List<EnseignantChercheur> findByEtablissement(String etablissement) {
		return enseignantChercheurRepository.findByEtablissement(etablissement);
	}

	@Override
	public List<Etudiant> getEtudiantsByEns(Long idEns) {
		EnseignantChercheur ens = enseignantChercheurRepository.findById(idEns).get();
		return etudiantRepository.findByEncadrant(ens);
	}

	
	
}

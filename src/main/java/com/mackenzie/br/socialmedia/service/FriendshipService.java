package com.mackenzie.br.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.FriendshipDAO;
import com.mackenzie.br.socialmedia.DAO.FriendshipRequestDAO;
import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.FriendshipDomain;
import com.mackenzie.br.socialmedia.domain.FriendshipRequestDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@Service
public class FriendshipService {
	
	public FriendshipService() {
		super();
	}

	@Autowired
	ProfessionalDAO professionalDAO;
	
	@Autowired
	FriendshipDAO friendshipDAO;
	
	@Autowired
	FriendshipRequestDAO friendshipRequestDAO;
	
	public String sendFriendshipRequest(List<ProfessionalDomain> professionals) {

		if (validateUser(professionals.get(0).getUserLogin()) && validateUser(professionals.get(1).getUserLogin())) {
		}else {
			return "Usuário Inválido";
		}

		List<ProfessionalDomain> professional0 = professionalDAO.findByUserLogin(professionals.get(0).getUserLogin());
		List<ProfessionalDomain> professional1 = professionalDAO.findByUserLogin(professionals.get(1).getUserLogin());
		
		if (professional0.get(0).getProfessionalID().equalsIgnoreCase(professional1.get(0).getProfessionalID()) ) {
			return "Você não pode enviar um request de amizade para você mesmo.";
		}
		
		List<FriendshipRequestDomain> listRequestSent = friendshipRequestDAO.findByProfessionalID1(professional0.get(0).getProfessionalID());
		List<FriendshipRequestDomain> listRequestReceived = friendshipRequestDAO.findByProfessionalID2(professional0.get(0).getProfessionalID());
		
		for (FriendshipRequestDomain request : listRequestSent){
			if (request.getProfessionalID2().equalsIgnoreCase(professional1.get(0).getProfessionalID())) {
				return "A solicitação de amizade para " +  professional1.get(0).getName() + " está pendente.";
			}
		}
		
		for (FriendshipRequestDomain request : listRequestReceived){
			if (request.getProfessionalID2().equalsIgnoreCase(professional0.get(0).getProfessionalID())) {
				return "O usuário " + professional1.get(0).getName() + " já lhe enviou uma requisição de amizade.";
				
			}
		}
		
		FriendshipRequestDomain friendshipRequest = new FriendshipRequestDomain();
		friendshipRequest.setProfessionalID1(professional0.get(0).getProfessionalID());
		friendshipRequest.setProfessionalID2(professional1.get(0).getProfessionalID());
		friendshipRequestDAO.insert(friendshipRequest);
		
		return "Solicitação de amizade enviada.";
		
	}

	public String acceptFriendshipRequest(List<ProfessionalDomain> professionals) {
		
		if (validateUser(professionals.get(0).getUserLogin()) && validateUser(professionals.get(1).getUserLogin())) {
		}else {
			return "Usuário Inválido";
		}

		List<ProfessionalDomain> professional0 = professionalDAO.findByUserLogin(professionals.get(0).getUserLogin());
		List<ProfessionalDomain> professional1 = professionalDAO.findByUserLogin(professionals.get(1).getUserLogin());
		
		if (professional0.get(0).getProfessionalID().equalsIgnoreCase(professional1.get(0).getProfessionalID()) ) {
			return "Você não pode enviar um request de amizade para você mesmo.";
		}
		
		List<FriendshipRequestDomain> listRequestReceived = friendshipRequestDAO.findByProfessionalID2(professional0.get(0).getProfessionalID());
		List<FriendshipDomain> listFriendships1 = friendshipDAO.findByProfessionalID1(professional0.get(0).getProfessionalID());
		List<FriendshipDomain> listFriendships2 = friendshipDAO.findByProfessionalID2(professional0.get(0).getProfessionalID());
		
		List<FriendshipDomain> listFriendships = new ArrayList<FriendshipDomain>();
		listFriendships.addAll(listFriendships1);
		listFriendships.addAll(listFriendships2);
		
		List<String> listFriendsID = new ArrayList<String>();
		
		for (FriendshipDomain friendship : listFriendships){
			if (friendship.getProfessionalID1().equalsIgnoreCase(professional0.get(0).getProfessionalID())) {
				listFriendsID.add(friendship.getProfessionalID2());
			}else {
				listFriendsID.add(friendship.getProfessionalID1());
			}
		}

		for (FriendshipRequestDomain request : listRequestReceived){
			if (request.getProfessionalID1().equalsIgnoreCase(professional1.get(0).getProfessionalID())) {
				
				FriendshipDomain friendship = new FriendshipDomain();
				friendship.setProfessionalID1(professional0.get(0).getProfessionalID());
				friendship.setProfessionalID2(professional1.get(0).getProfessionalID());
				
				friendshipRequestDAO.delete(request);
				friendshipDAO.save(friendship);

				
				return "O usuário " + professional1.get(0).getName() + " foi adicionado a sua lista de amigos.";
			}
		}

		return "Não existe nenhuma requisição de amizade de : " + professional1.get(0).getName() + ".";
		}
	
	public String rejectFriendshipRequest(List<ProfessionalDomain> professionals) {
		
		if (validateUser(professionals.get(0).getUserLogin()) && validateUser(professionals.get(1).getUserLogin())) {
		}else {
			return "Usuário Inválido";
		}
		
		List<ProfessionalDomain> professional0 = professionalDAO.findByUserLogin(professionals.get(0).getUserLogin());
		List<ProfessionalDomain> professional1 = professionalDAO.findByUserLogin(professionals.get(1).getUserLogin());
		
		if (professional0.get(0).getProfessionalID().equalsIgnoreCase(professional1.get(0).getProfessionalID()) ) {
			return "Você não pode enviar um request de amizade para você mesmo.";
		}
		
		List<FriendshipRequestDomain> listRequestReceived = friendshipRequestDAO.findByProfessionalID2(professional0.get(0).getProfessionalID());
		
		for (FriendshipRequestDomain request : listRequestReceived){
			if (request.getProfessionalID1().equalsIgnoreCase(professional1.get(0).getProfessionalID())) {
				friendshipRequestDAO.delete(request);
				return "Solicitação de amizade recusada.";
			}
		}
		
		return "Nenhuma solicitação de " + professional1.get(0).getName() + ", foi encontrada.";
	}
	
	public boolean validateUser(String userLogin) {
		for (ProfessionalDomain professional : professionalDAO.findAll()){
			if (professional.getUserLogin().equalsIgnoreCase(userLogin)) {
				return true;
			}
		}
		return false;
	}

}
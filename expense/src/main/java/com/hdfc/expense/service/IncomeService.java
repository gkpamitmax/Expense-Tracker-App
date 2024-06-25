package com.hdfc.expense.service;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hdfc.expense.model.Income;
import com.hdfc.expense.repository.IncomeRepository;



@Service
public class IncomeService {
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	private IncomeRepository incomeRepository;

    private HttpEntity<String> createHttpEntityWithAccessToken(String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+jwtToken);
        System.out.println(headers);
        return new HttpEntity<>(headers);
    }
	
	
	public List<Income> getAllIncomesByUsername(String username) {
		return incomeRepository.findByUsername(username);
	}

	public Income addIncome(Income income) {
		return incomeRepository.save(income);
	}

	public void deleteIncome(Long incomeId) {
		incomeRepository.deleteById(incomeId);
	}

	public Income updateIncome(Long incomeId, Income updatedIncome) {
		Income existingIncome = incomeRepository.findById(incomeId)
				.orElseThrow(() -> new NotFoundException("Income not found with ID: " + incomeId));

		existingIncome.setDate(updatedIncome.getDate());
		existingIncome.setAmount(updatedIncome.getAmount());
		existingIncome.setCategory(updatedIncome.getCategory());
		existingIncome.setDescription(updatedIncome.getDescription());

		return incomeRepository.save(existingIncome);
	}
	
//    public String hello(String jwtToken) {
//    	String gatewayUrl = "http://localhost:8080";
//    	String destinationUrl = gatewayUrl + "/goals/welcome";
//    	 try {
//			ResponseEntity<String> response = restTemplate.exchange(
//			         destinationUrl,
//			         HttpMethod.GET,
//			         createHttpEntityWithAccessToken(jwtToken),
//			         String.class);
//			  String responseBody = response.getBody();
//			 return responseBody;
//		} catch (RestClientException e) {
//			e.printStackTrace();
//			return null;
//		}
//    }
}

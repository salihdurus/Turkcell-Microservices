package com.turkcellGY.invoiceservice.business.rules;


import com.turkcellGY.commonpackage.utils.constans.Messages;
import com.turkcellGY.commonpackage.utils.exceptions.BusinessException;
import com.turkcellGY.invoiceservice.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class InvoiceBusinessRules {
    private final InvoiceRepository repository;
    public void checkIfInvoiceExists(UUID id){
        if(!repository.existsById(id)){
            throw new BusinessException(Messages.Invoice.NotExists);
        }
    }
}

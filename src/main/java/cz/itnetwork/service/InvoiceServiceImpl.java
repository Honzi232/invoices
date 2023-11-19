package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        entity = invoiceRepository.save(entity);
        return invoiceMapper.toDTO(entity);
    }

    @Override
    public List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);
        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoice(long invoiceId) {
        InvoiceEntity invoice = invoiceRepository.getReferenceById(invoiceId);
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public void removeInvoice(Long invoiceId) {
        InvoiceEntity invoiceToDelete = invoiceRepository.findById(invoiceId).orElseThrow(EntityNotFoundException::new);
        invoiceRepository.delete(invoiceToDelete);
    }

    @Override
    public InvoiceDTO updateInvoice(long invoiceId, InvoiceDTO invoiceDTO) {
        invoiceDTO.setId(invoiceId);
        InvoiceEntity invoiceEntity = invoiceRepository.getReferenceById(invoiceId);
        invoiceEntity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        invoiceEntity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));
        invoiceMapper.updateInvoiceEntity(invoiceDTO, invoiceEntity);
        return invoiceMapper.toDTO(invoiceRepository.save(invoiceEntity));
    }

    @Override
    public List<InvoiceDTO> getInvoicesByBuyer(long personId) {
        return invoiceRepository.getAllByBuyerId(personId)
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getInvoicesBySeller(long personId) {
        return invoiceRepository.getAllBySellerId(personId)
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceStatisticDTO invoiceStatisticDTO() {
        InvoiceStatisticDTO invoiceStatisticDTO = new InvoiceStatisticDTO();
        invoiceStatisticDTO.setCurrentYearSum(invoiceRepository.getCurrentYearSum());
        invoiceStatisticDTO.setAllTimeSum(invoiceRepository.getAllTimeSum());
        invoiceStatisticDTO.setInvoicesCount(invoiceRepository.getInvoiceCount());
        return invoiceStatisticDTO;
    }
}










package com.develhope.spring.admins;

import com.develhope.spring.customers.CustomerRepository;
import com.develhope.spring.loginAuth.IdLog;
import com.develhope.spring.orders.forrental.RentalOrderRepository;
import com.develhope.spring.orders.forsale.SaleOrder;
import com.develhope.spring.orders.forsale.SaleOrderRepository;
import com.develhope.spring.orders.forsale.SaleOrderStatus;
import com.develhope.spring.sellers.SellerRepository;
import com.develhope.spring.vehicle.VehicleEntity;
import com.develhope.spring.vehicle.forrental.VehicleForRentalRepository;
import com.develhope.spring.vehicle.forsale.StatusSale;
import com.develhope.spring.vehicle.forsale.VehicleForSale;
import com.develhope.spring.vehicle.forsale.VehicleForSaleRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


//Cambiare lo stato di un veicolo
//Creare un noleggio per un utente
//Cancellare un noleggio per un utente
//Modificare un noleggio per un utente

//Verificare un venditore quante vendite ha fatto in un determinato periodo di tempo
//Verificare un venditore quanti soldi ha generato in un determinato periodo di tempo
//Verificare il guadagno del salone in un determinato periodo
//Verificare i veicoli attualmente ordinabili/acquistabili/non disponibili/nuovi/usati
//Cancellare un utente
//Modificare un utente
//Cancellare un venditore
//Modificare un venditore
//Ottenere il veicolo più venduto in un dato periodo
//Ottenere il veicolo con valore più alto venduto fino a quel dato istante
//Ottenere il veicolo più rircercato/ordinato
@Service
public class AdminService {
    @Autowired
    private IdLog idLog;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private VehicleForRentalRepository vehicleForRentalRepository;
    @Autowired
    private VehicleForSaleRepository vehicleForSaleRepository;
    @Autowired
    private SaleOrderRepository saleOrderRepository;
    @Autowired
    private RentalOrderRepository rentalOrderRepository;

    //Aggiungere un veicolo
    public VehicleForSale addVehicleSale(VehicleEntity vehicleSale) {
        VehicleForSale vehicle = new VehicleForSale();
        vehicle.setStatus(vehicle.getStatus());
        vehicle.setLicensePlate(vehicle.getLicensePlate());
        vehicle.setBrand(vehicle.getBrand());
        vehicle.setModel(vehicle.getModel());
        vehicle.setDisplacement(vehicle.getDisplacement());
        vehicle.setColor(vehicle.getColor());
        vehicle.setPower(vehicle.getPower());
        vehicle.setTransmission(vehicle.getTransmission());
        vehicle.setRegistrationYear(vehicle.getRegistrationYear());
        vehicle.setEngine(vehicle.getEngine());
        vehicle.setType(vehicle.getType());
        vehicle.setId(vehicle.getId());
        vehicle.setListPrice(vehicle.getListPrice());
        vehicle.setDiscountPercentage(vehicle.getDiscountPercentage());
        vehicle.setOptionals(vehicle.getOptionals());
        vehicle.setIsNew(vehicle.getIsNew());
        vehicle.setStatus(vehicle.getStatus());
        return vehicle;
    }

    //Modificare un veicolo
    @SneakyThrows
    public VehicleEntity updateVehicle(VehicleEntity vehicleEntity, Long vehicleId) {
        VehicleEntity newVehicle = new VehicleEntity();
        if (vehicleForSaleRepository.existsById(vehicleId)) {
            newVehicle = vehicleForSaleRepository.findById(vehicleId).get();
        } else if (vehicleForRentalRepository.existsById(vehicleId)) {
            newVehicle = vehicleForRentalRepository.findById(vehicleId).get();
        }
        return newVehicle;
    }


    //Cancellare un veicolo
    @SneakyThrows
    public void deleteVehicle(Long vehicleId) {
        if (vehicleForSaleRepository.existsById(vehicleId)) {
            vehicleForSaleRepository.deleteById(vehicleId);
        } else if (vehicleForRentalRepository.existsById(vehicleId)) {
            vehicleForRentalRepository.deleteById(vehicleId);
        }
    }



//Creare un ordine per un utente
public SaleOrder createSaleOrder(SaleOrder saleOrder, Long customerId, Long vehicleId, Long sellerId) {
    if (idLog.getRole().equals("ADMIN")) {
        return saleOrderRepository.save(addNewSaleOrder(saleOrder, customerId, vehicleId, sellerId));
    } else {
        return null;
    }
}

public SaleOrder addNewSaleOrder(SaleOrder saleOrder, Long customerId, Long vehicleId, Long sellerId) {
    VehicleForSale vehicleSale = vehicleForSaleRepository.findById(vehicleId).get();
    if (vehicleSale.getStatus().equals(StatusSale.ORDERABLE)) {
        SaleOrder newSaleOrder = new SaleOrder();
        newSaleOrder.setSaleOrderStatus(SaleOrderStatus.ORDER);
        newSaleOrder.setSaleOrderStatement(saleOrder.getSaleOrderStatement());
        newSaleOrder.setStatusPayment(saleOrder.getStatusPayment());
        newSaleOrder.setVehicleId(vehicleSale);
        newSaleOrder.setCustomerId(customerRepository.findById(customerId).get());
        newSaleOrder.setSellerId(sellerRepository.findById(sellerId).get());
        return newSaleOrder;
    } else {
        return null;
    }
}

//Modificare un ordine per un utente
public SaleOrder updateSaleOrder(SaleOrder saleOrder, Long saleOrderId) {
    if (idLog.getRole().equals("ADMIN")) {
        SaleOrder newOrder = saleOrderRepository.findById(saleOrderId).get();
        if (saleOrder.getSaleOrderStatus() != null) {
            newOrder.setSaleOrderStatus(saleOrder.getSaleOrderStatus());
        }
        if (saleOrder.getSaleOrderStatement() != null) {
            newOrder.setSaleOrderStatement(saleOrder.getSaleOrderStatement());
        }
        if (saleOrder.getStatusPayment() != null) {
            newOrder.setStatusPayment(saleOrder.getStatusPayment());
        }
        return saleOrderRepository.save(newOrder);
    } else {
        return null;
    }
}

//Cancellare un ordine per un utente
public SaleOrder updateDeleted(Long saleOrderId) {
    saleOrderRepository.updateDeletedById(saleOrderId);
    return saleOrderRepository.findById(saleOrderId).get();
}

//Creare un acquisto per un utente
public SaleOrder createPurchase(SaleOrder saleOrder, Long sellerId, Long vehicleId, Long customerId) {
    if (idLog.getRole().equals("ADMIN")) {
        return saleOrderRepository.save(addNewPurchase(saleOrder, sellerId, vehicleId, customerId));
    } else {
        return null;
    }
}

public SaleOrder addNewPurchase(SaleOrder saleOrder, Long sellerId, Long vehicleId, Long customerId) {
    VehicleForSale vehicleSale = vehicleForSaleRepository.findById(vehicleId).get();
    if (vehicleSale.getStatus().equals(StatusSale.SELLABLE)) {
        SaleOrder newSaleOrder = new SaleOrder();
        newSaleOrder.setSaleOrderStatus(SaleOrderStatus.PURCHASE);
        newSaleOrder.setStatusSale(saleOrder.getStatusSale());
        newSaleOrder.setStatusPayment(saleOrder.getStatusPayment());
        newSaleOrder.setVehicleId(vehicleSale);
        newSaleOrder.setCustomerId(customerRepository.findById(customerId).get());
        newSaleOrder.setSellerId(sellerRepository.findById(sellerId).get());
        return newSaleOrder;
    } else {
        return null;
    }
}

//Modificare un acquisto per un utente
public SaleOrder updatePurchase(SaleOrder saleOrder, Long saleOrderId) {
    if (idLog.getRole().equals("ADMIN")) {
        SaleOrder newOrder = saleOrderRepository.findById(saleOrderId).get();
        if (saleOrder.getSaleOrderStatus() != null) {
            newOrder.setSaleOrderStatus(saleOrder.getSaleOrderStatus());
        }
        if (saleOrder.getSaleOrderStatement() != null) {
            newOrder.setSaleOrderStatement(saleOrder.getSaleOrderStatement());
        }
        if (saleOrder.getStatusPayment() != null) {
            newOrder.setStatusPayment(saleOrder.getStatusPayment());
        }
        return saleOrderRepository.save(newOrder);
    } else {
        return null;
    }
}

//Cancellare un acquisto per un utente
public SaleOrder updateDeletedPurchase(Long saleOrderId) {
    saleOrderRepository.updateDeletedById(saleOrderId);
    return saleOrderRepository.findById(saleOrderId).get();

}

}


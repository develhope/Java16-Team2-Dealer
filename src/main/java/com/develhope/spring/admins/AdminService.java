package com.develhope.spring.admins;

import com.develhope.spring.customers.CustomerRepository;
import com.develhope.spring.loginAuth.IdLog;
import com.develhope.spring.orders.forrental.RentalOrderRepository;
import com.develhope.spring.orders.forsale.SaleOrder;
import com.develhope.spring.orders.forsale.SaleOrderRepository;
import com.develhope.spring.orders.forsale.SaleOrderStatus;
import com.develhope.spring.sellers.SellerRepository;
import com.develhope.spring.vehicle.forrental.VehicleForRentalRepository;
import com.develhope.spring.vehicle.forsale.StatusSale;
import com.develhope.spring.vehicle.forsale.VehicleForSale;
import com.develhope.spring.vehicle.forsale.VehicleForSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public SaleOrder updateDeleted(Long saleOrderId) {
        saleOrderRepository.updateDeletedById(saleOrderId);
        return saleOrderRepository.findById(saleOrderId).get();
    }

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
    public SaleOrder updateDeletedPurchase(Long saleOrderId){
        saleOrderRepository.updateDeletedById(saleOrderId);
        return saleOrderRepository.findById(saleOrderId).get();

    }

}


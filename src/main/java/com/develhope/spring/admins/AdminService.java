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
        } else{return null;}
    }

}

package br.com.zupacademy.giovannimoratto.ecommerce.purchase_gateway.event;

import br.com.zupacademy.giovannimoratto.ecommerce.purchase_costumer.PurchaseModel;

/**
 * @Author giovanni.moratto
 */

public interface SuccessEvent {

    void process(PurchaseModel purchase);
}

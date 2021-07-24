package br.com.zupacademy.giovannimoratto.ecommerce.purchase_response;

import br.com.zupacademy.giovannimoratto.ecommerce.purchase.BankTransactionModel;
import br.com.zupacademy.giovannimoratto.ecommerce.purchase_request.PurchaseModel;

/**
 * @Author giovanni.moratto
 */

public interface GatewayResponse {

    BankTransactionModel create(PurchaseModel purchase);

}

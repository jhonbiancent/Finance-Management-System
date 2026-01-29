<script setup>
import { ref, onMounted } from 'vue';
import api from '@/services/api';

const transactions = ref([]);
const loading = ref(false);
const error = ref(null);

const fetchTransactions = async () => {
  loading.value = true;
  try {
    const response = await api.getTransactions();
    transactions.value = response.data;
    error.value = null;
  } catch (err) {
    console.error('Failed to fetch transactions:', err);
    error.value = 'Failed to fetch transactions. Please try again later.';
  } finally {
    loading.value = false;
  }
};

onMounted(() => {
  fetchTransactions();
});
</script>

<template>
  <div class="view-container">
    <header class="top-bar">
      <h1>Transactions</h1>
      <div class="actions">
        <!-- Add/Edit buttons will be added in Phase 3 with role-based logic -->
        <button class="btn-secondary">Export</button>
      </div>
    </header>

    <div class="content-area">
      <div v-if="error" class="banner error">
        {{ error }}
      </div>
      
      <div v-if="loading" class="text-center">Loading...</div>

      <div class="spreadsheet-container">
        <table class="spreadsheet">
          <thead>
            <tr>
              <th>ID</th>
              <th>Subsidiary</th>
              <th>Disbursement</th>
              <th>Logistics</th>
              <th>Product</th>
              <th>AR</th>
              <th>Controller</th>
              <th>AP</th>
              <th>User</th>
              <th>Check #</th>
              <th>Check Date</th>
              <th>Status</th>
              <th>Balance</th>
              <th>Due Date</th>
              <th>Terms (Days)</th>
              <th>Mode</th>
              <th>Type</th>
              <th>Qty</th>
              <th>Fuel Purchase</th>
              <th>Invoice #</th>
              <th>Freight Amt</th>
              <th>Freight Bill</th>
              <th>Comm. Name</th>
              <th>Comm. Amt</th>
              <th>Comm. On Bill</th>
              <th>Payments</th>
              <th>Remarks</th>
              <th>Backtrack</th>
              <th>Status 2</th>
              <th>Status 3</th>
              <th>Group</th>
              <th>Revenue Cat.</th>
              <th>Billing #</th>
              <th>Description</th>
              <th>BMMI Customer</th>
              <th>TIN</th>
              <th>VAT/Non-VAT</th>
              <th>VAT Amt</th>
              <th>Address</th>
              <th>Gross Sales</th>
              <th>Discount</th>
              <th>Net Sales</th>
              <th>Sales VAT Excl.</th>
              <th>EWT</th>
              <th>Service Chg.</th>
              <th>Stripping Fee</th>
              <th>Return</th>
              <th>Interest Inc.</th>
              <th>Particulars</th>
              <th>Bank</th>
              <th>From Bank Sub</th>
              <th>To Bank Sub</th>
              <th>Amount</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="t in transactions" :key="t.id">
              <td>{{ t.id }}</td>
              <td>{{ t.subsidiaryName }}</td>
              <td>{{ t.disbursementName }}</td>
              <td>{{ t.logisticsInfo }}</td>
              <td>{{ t.productName }}</td>
              <td>{{ t.accountsReceivableName }}</td>
              <td>{{ t.controllerName }}</td>
              <td>{{ t.accountsPayableName }}</td>
              <td>{{ t.userName }}</td>
              <td>{{ t.checkNumber }}</td>
              <td>{{ t.checkDate }}</td>
              <td>{{ t.status }}</td>
              <td>{{ t.balance }}</td>
              <td>{{ t.dueDate }}</td>
              <td>{{ t.termsOfDays }}</td>
              <td>{{ t.mode }}</td>
              <td>{{ t.type }}</td>
              <td>{{ t.quantity }}</td>
              <td>{{ t.fuelPurchase }}</td>
              <td>{{ t.invoiceBillingNumber }}</td>
              <td>{{ t.freightAmount }}</td>
              <td>{{ t.freightBilling }}</td>
              <td>{{ t.commissionName }}</td>
              <td>{{ t.commissionAmount }}</td>
              <td>{{ t.commissionOnBilling }}</td>
              <td>{{ t.payments }}</td>
              <td>{{ t.remarks }}</td>
              <td>{{ t.backtrack }}</td>
              <td>{{ t.status2 }}</td>
              <td>{{ t.status3 }}</td>
              <td>{{ t.group }}</td>
              <td>{{ t.revenueCategory }}</td>
              <td>{{ t.billingNumber }}</td>
              <td>{{ t.description }}</td>
              <td>{{ t.bmmiCustomer }}</td>
              <td>{{ t.tin }}</td>
              <td>{{ t.vatNonvat }}</td>
              <td>{{ t.vat }}</td>
              <td>{{ t.address }}</td>
              <td>{{ t.grossSales }}</td>
              <td>{{ t.discount }}</td>
              <td>{{ t.netSales }}</td>
              <td>{{ t.salesVatExclusive }}</td>
              <td>{{ t.ewt }}</td>
              <td>{{ t.serviceCharge }}</td>
              <td>{{ t.strippingFee }}</td>
              <td>{{ t.returnValue }}</td>
              <td>{{ t.interestIncome }}</td>
              <td>{{ t.particulars }}</td>
              <td>{{ t.bank }}</td>
              <td>{{ t.fromBankSub }}</td>
              <td>{{ t.toBankSub }}</td>
              <td>{{ t.amount }}</td>
            </tr>
            <tr v-if="transactions.length === 0 && !loading">
              <td :colspan="52" class="text-center">No transactions found.</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
.view-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.top-bar {
  background-color: var(--header-bg);
  padding: 1rem 2rem;
  border-bottom: 1px solid var(--border-color);
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.top-bar h1 {
  margin: 0;
  font-size: 1.5rem;
}

.content-area {
  flex: 1;
  padding: 2rem;
  overflow-y: auto;
}

.spreadsheet-container {
  background: var(--card-bg);
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.05);
  overflow-x: auto; /* Enable horizontal scrolling */
  border: 1px solid var(--border-color);
  color: var(--text-color);
}

.spreadsheet {
  width: 100%;
  border-collapse: collapse;
  font-size: 0.9rem;
  white-space: nowrap; /* Prevent table cell content from wrapping */
}

.spreadsheet th, .spreadsheet td {
  padding: 0.75rem 1rem;
  text-align: left;
  border-bottom: 1px solid var(--border-color);
  color: var(--text-color);
}

.spreadsheet th {
  background-color: var(--bg-color);
  font-weight: 600;
  color: var(--text-muted);
  text-transform: uppercase;
  font-size: 0.75rem;
  letter-spacing: 0.5px;
  position: sticky;
  top: 0;
}

.spreadsheet tr:last-child td {
  border-bottom: none;
}

.spreadsheet tr:hover {
  background-color: var(--bg-color);
}

.text-center { text-align: center; }

.banner {
  padding: 1rem;
  margin-bottom: 1rem;
  border-radius: 6px;
  font-size: 0.9rem;
}
.banner.error {
  background-color: var(--error-bg);
  color: var(--error-text);
  border: 1px solid var(--error-text);
}

.btn-secondary {
  background-color: transparent;
  border: 1px solid #ccc;
  color: var(--text-color);
  margin-left: 0.5rem;
}
</style>
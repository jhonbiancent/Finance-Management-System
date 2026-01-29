<script setup>
import { ref, onMounted } from 'vue'
import TransactionModal from '../components/TransactionModal.vue'
import api from '@/services/api'

const transactions = ref([])
const loading = ref(false)
const error = ref(null)

const isModalOpen = ref(false)
const isEditing = ref(false)
const currentTransaction = ref(null)

const fetchTransactions = async () => {
  loading.value = true
  try {
    const response = await api.getTransactions()
    transactions.value = response.data
    error.value = null
  } catch (err) {
    console.error('Failed to fetch transactions:', err)
    error.value = 'Failed to fetch transactions. Please try again later.'
  } finally {
    loading.value = false
  }
}

const openNewTransactionModal = () => {
  isEditing.value = false
  currentTransaction.value = null
  isModalOpen.value = true
}

const openEditTransactionModal = (transaction) => {
  isEditing.value = true
  currentTransaction.value = transaction
  isModalOpen.value = true
}

const handleSave = async (formData) => {
  try {
    if (isEditing.value) {
      await api.updateTransaction(currentTransaction.value.transactionId, formData)
    } else {
      await api.createTransaction(formData)
    }
    await fetchTransactions()
    isModalOpen.value = false
  } catch (e) {
    alert('Error saving transaction: ' + e.message)
  }
}

const handleDelete = async (id) => {
  if (!confirm('Are you sure you want to delete this transaction?')) return
  try {
    await api.deleteTransaction(id)
    await fetchTransactions()
  } catch (e) {
    alert('Error deleting transaction: ' + e.message)
  }
}

onMounted(() => {
  fetchTransactions()
})
</script>

<template>
  <div class="view-container">
    <header class="top-bar">
      <h1>Transactions</h1>
      <div class="actions">
        <button class="btn-primary" @click="openNewTransactionModal">+ New Entry</button>
        <button class="btn-secondary">Export</button>
      </div>
    </header>

    <div class="content-area">
      <div v-if="error" class="banner error">
        {{ error }}
      </div>
      
      <div class="spreadsheet-container">
        <table class="spreadsheet">
          <thead>
            <tr>
              <th>Date</th>
              <th>Type</th>
              <th>Description</th>
              <th>Amount</th>
              <th>Category</th>
              <th>Payment Method</th>
              <th>Status</th>
              <th>Beneficiary</th>
              <th>Reference Number</th>
              <th>Invoice Number</th>
              <th>Budget Code</th>
              <th>Comments</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="t in transactions" :key="t.transactionId">
              <td>{{ t.checkDate }}</td>
              <td>{{ t.type }}</td>
              <td>{{ t.description }}</td>
              <td>{{ t.amount.toFixed(2) }}</td>
              <td><span class="badge">{{ t.revenueCategory }}</span></td>
              <td>{{ t.mode }}</td>
              <td>{{ t.status }}</td>
              <td>{{ t.bmmiCustomer }}</td>
              <td>{{ t.checkNumber }}</td>
              <td>{{ t.invoiceBillingNumber }}</td>
              <td>{{ t.particulars }}</td>
              <td>{{ t.remarks }}</td>
              <td class="actions-cell">
                <button class="btn-icon" @click="openEditTransactionModal(t)">✏️</button>
                <button class="btn-icon" @click="handleDelete(t.transactionId)">🗑️</button>
              </td>
            </tr>
            <tr v-if="transactions.length === 0 && !loading">
              <td colspan="13" class="text-center">No transactions found.</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <TransactionModal 
      :is-open="isModalOpen"
      :is-editing="isEditing"
      :initial-data="currentTransaction"
      @close="isModalOpen = false"
      @submit="handleSave"
    />
  </div>
</template>

<style scoped>
/* Reuse styles from App.vue but scoped to this view */
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
  flex-wrap: wrap; /* Allow items to wrap to the next line */
  gap: 1rem; /* Add some space between items */
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
  overflow-x: auto; /* Add horizontal scrolling */
  border: 1px solid var(--border-color);
  color: var(--text-color);
}

.spreadsheet {
  width: 100%;
  border-collapse: collapse;
  font-size: 0.95rem;
}

.spreadsheet th, .spreadsheet td {
  padding: 1rem;
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
}

.spreadsheet tr:last-child td {
  border-bottom: none;
}

.spreadsheet tr:hover {
  background-color: var(--bg-color);
}

.text-right { text-align: right; }
.text-center { text-align: center; }

.status {
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  font-size: 0.75rem;
  font-weight: 600;
}
.status.income { background-color: var(--success-bg); color: var(--success-text); }
.status.expense { background-color: var(--error-bg); color: var(--error-text); }

.badge {
  background-color: var(--input-bg);
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  font-size: 0.8rem;
  color: var(--text-color);
}

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

.btn-primary {
  background-color: #3498db;
  color: white;
  border: none;
}
.btn-secondary {
  background-color: transparent;
  border: 1px solid #ccc;
  color: var(--text-color);
  margin-left: 0.5rem;
}
.btn-icon {
  background: none;
  border: none;
  cursor: pointer;
  padding: 0.2rem;
  opacity: 0.6;
}
.btn-icon:hover { opacity: 1; }
</style>

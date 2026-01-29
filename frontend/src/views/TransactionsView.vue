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
      await api.updateTransaction(currentTransaction.value.id, formData)
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
        <button class="btn btn-primary" @click="openNewTransactionModal"><i class="fas fa-plus"></i> New Entry</button>
        <button class="btn btn-secondary"><i class="fas fa-file-export"></i> Export</button>
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
            <th>OR Number</th>
            <th>Description</th>
            <th>Category</th>
            <th>Type</th>
            <th class="text-right">Amount</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="t in transactions" :key="t.id">
            <td>{{ t.date }}</td>
            <td>{{ t.orNumber }}</td>
            <td>{{ t.description }}</td>
            <td><span class="badge">{{ t.category }}</span></td>
            <td>
                <span :class="['status', t.type === 'INCOME' ? 'income' : 'expense']">
                  {{ t.type }}
                </span>
            </td>
            <td class="text-right">{{ t.amount.toFixed(2) }}</td>
            <td>{{ t.status }}</td>
            <td class="actions-cell">
              <button class="btn-icon" @click="openEditTransactionModal(t)" title="Edit"><i class="fas fa-pencil-alt"></i></button>
              <button class="btn-icon" @click="handleDelete(t.id)" title="Delete"><i class="fas fa-trash-alt"></i></button>
            </td>
          </tr>
          <tr v-if="transactions.length === 0 && !loading">
            <td colspan="8" class="text-center">No transactions found.</td>
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
  height: 64px; /* Fixed height to match sidebar logo section */
  padding: 0 2rem;
  border-bottom: 1px solid var(--border-color);
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.top-bar h1 {
  margin: 0;
  font-size: 1.5rem;
}

.actions {
  display: flex;
  gap: 1rem;
  align-items: center;
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
  overflow: hidden;
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

/* New Button Styles */
.btn {
  padding: 0.5rem 1rem;
  border-radius: 6px;
  font-size: 0.9rem;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s ease;
  border: 1px solid transparent;
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
}

.btn-primary {
  background-color: #3498db;
  color: white;
}

.btn-primary:hover {
  background-color: #2980b9;
}

.btn-secondary {
  background-color: var(--input-bg);
  color: var(--text-color);
  border-color: var(--input-border);
}

.btn-secondary:hover {
  background-color: var(--bg-color);
  border-color: var(--text-color);
}

.btn-icon {
  background: none;
  border: none;
  cursor: pointer;
  padding: 0.4rem;
  opacity: 0.7;
  color: var(--text-muted);
}
.btn-icon:hover {
  opacity: 1;
  color: var(--text-color);
  background-color: var(--bg-color);
  border-radius: 50%;
}
.actions-cell .btn-icon:first-child:hover {
  color: var(--success-text);
}
.actions-cell .btn-icon:last-child:hover {
  color: var(--error-text);
}
</style>
<script setup>
import { ref, onMounted } from 'vue'
import TransactionModal from '../components/TransactionModal.vue'

const transactions = ref([])
const loading = ref(false)
const error = ref(null)

const isModalOpen = ref(false)
const isEditing = ref(false)
const currentTransaction = ref(null)

// Mock data
const mockData = [
  { id: 1, date: '2026-01-20', description: 'Office Supplies', category: 'Operations', amount: 150.00, type: 'EXPENSE' },
  { id: 2, date: '2026-01-21', description: 'Client Payment', category: 'Sales', amount: 5000.00, type: 'INCOME' },
  { id: 3, date: '2026-01-22', description: 'Software License', category: 'IT', amount: 299.99, type: 'EXPENSE' },
]

const API_URL = 'http://localhost:8080/api/transactions'

const fetchTransactions = async () => {
  loading.value = true
  try {
    const response = await fetch(API_URL)
    if (!response.ok) throw new Error('Network response was not ok')
    transactions.value = await response.json()
    error.value = null
  } catch (err) {
    console.warn('Backend not reachable, using mock data:', err)
    transactions.value = mockData
    error.value = 'Backend unreachable. Showing mock data.'
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
    const method = isEditing.value ? 'PUT' : 'POST'
    const url = isEditing.value ? `${API_URL}/${currentTransaction.value.id}` : API_URL
    
    const response = await fetch(url, {
      method: method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formData)
    })

    if (!response.ok) throw new Error('Failed to save')
    await fetchTransactions()
    isModalOpen.value = false
  } catch (e) {
    alert('Error saving transaction: ' + e.message)
  }
}

const handleDelete = async (id) => {
  if (!confirm('Are you sure you want to delete this transaction?')) return
  try {
    const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' })
    if (!response.ok) throw new Error('Failed to delete')
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
              <th>Description</th>
              <th>Category</th>
              <th>Type</th>
              <th class="text-right">Amount</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="t in transactions" :key="t.id">
              <td>{{ t.date }}</td>
              <td>{{ t.description }}</td>
              <td><span class="badge">{{ t.category }}</span></td>
              <td>
                <span :class="['status', t.type === 'INCOME' ? 'income' : 'expense']">
                  {{ t.type }}
                </span>
              </td>
              <td class="text-right">{{ t.amount.toFixed(2) }}</td>
              <td class="actions-cell">
                <button class="btn-icon" @click="openEditTransactionModal(t)">✏️</button>
                <button class="btn-icon" @click="handleDelete(t.id)">🗑️</button>
              </td>
            </tr>
            <tr v-if="transactions.length === 0 && !loading">
              <td colspan="6" class="text-center">No transactions found.</td>
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
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.05);
  overflow: hidden;
  border: 1px solid var(--border-color);
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
}

.spreadsheet th {
  background-color: #f8f9fa;
  font-weight: 600;
  color: #555;
  text-transform: uppercase;
  font-size: 0.75rem;
  letter-spacing: 0.5px;
}

.spreadsheet tr:last-child td {
  border-bottom: none;
}

.spreadsheet tr:hover {
  background-color: #f8f9fa;
}

.text-right { text-align: right; }
.text-center { text-align: center; }

.status {
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  font-size: 0.75rem;
  font-weight: 600;
}
.status.income { background-color: #e8f5e9; color: #27ae60; }
.status.expense { background-color: #ffebee; color: #c0392b; }

.badge {
  background-color: #edf2f7;
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  font-size: 0.8rem;
  color: #4a5568;
}

.banner {
  padding: 1rem;
  margin-bottom: 1rem;
  border-radius: 6px;
  font-size: 0.9rem;
}
.banner.error {
  background-color: #fff5f5;
  color: #c53030;
  border: 1px solid #feb2b2;
}

.btn-primary {
  background-color: #3498db;
  color: white;
  border: none;
}
.btn-secondary {
  background-color: transparent;
  border: 1px solid #ccc;
  color: #333;
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

<script setup>
import { ref, reactive, watch } from 'vue'

const props = defineProps({
  isOpen: Boolean,
  isEditing: Boolean,
  initialData: Object
})

const emit = defineEmits(['close', 'submit'])

  const form = reactive({
  orNumber: '',
  description: '',
  amount: '',
  date: '',
  type: 'EXPENSE',
  category: '',
  status: 'PENDING'
})

// Reset or Populate form when modal opens
watch(() => props.isOpen, (newVal) => {
  if (newVal) {
    if (props.isEditing && props.initialData) {
      Object.assign(form, props.initialData)
      // Ensure date is formatted for input type="date"
      if (form.date && form.date.includes('T')) {
          form.date = form.date.split('T')[0]
      }
    } else {
      // Reset
      form.orNumber = ''
      form.description = ''
      form.amount = ''
      form.date = new Date().toISOString().split('T')[0]
      form.type = 'EXPENSE'
      form.category = ''
      form.status = 'PENDING'
    }
  }
})

const handleSubmit = () => {
  // Basic validation could go here
  emit('submit', { ...form })
}
</script>

<template>
  <div v-if="isOpen" class="modal-overlay" @click.self="$emit('close')">
    <div class="modal-content">
      <header class="modal-header">
        <h3>{{ isEditing ? 'Edit Transaction' : 'New Transaction' }}</h3>
        <button class="close-btn" @click="$emit('close')">&times;</button>
      </header>
      
      <form @submit.prevent="handleSubmit" class="modal-form">
        <div class="form-group">
          <label>Date</label>
          <input type="date" v-model="form.date" required />
        </div>

        <div class="form-group">
          <label>OR Number</label>
          <input type="text" v-model="form.orNumber" placeholder="e.g. OR-001" required />
        </div>

        <div class="form-group">
          <label>Description</label>
          <input type="text" v-model="form.description" placeholder="e.g. Office Supplies" required />
        </div>

        <div class="form-group">
          <label>Amount</label>
          <input type="number" step="0.01" v-model="form.amount" placeholder="0.00" required />
        </div>

        <div class="form-group">
          <label>Type</label>
          <select v-model="form.type">
            <option value="INCOME">Income</option>
            <option value="EXPENSE">Expense</option>
          </select>
        </div>

        <div class="form-group">
          <label>Category</label>
          <input type="text" v-model="form.category" placeholder="e.g. Operations, Sales" required />
        </div>
        
        <div class="form-group">
          <label>Status</label>
          <select v-model="form.status">
            <option value="PENDING">Pending</option>
            <option value="APPROVED">Approved</option>
            <option value="REJECTED">Rejected</option>
          </select>
        </div>

        <div class="modal-actions">
          <button type="button" class="btn-secondary" @click="$emit('close')">Cancel</button>
          <button type="submit" class="btn-primary">Save</button>
        </div>
      </form>    </div>
  </div>
</template>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  border-radius: 8px;
  width: 100%;
  max-width: 500px;
  box-shadow: 0 4px 6px rgba(0,0,0,0.1);
  overflow: hidden;
}

.modal-header {
  padding: 1rem 1.5rem;
  border-bottom: 1px solid #eee;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.modal-header h3 {
  margin: 0;
  font-size: 1.25rem;
}

.close-btn {
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  padding: 0;
  line-height: 1;
}

.modal-form {
  padding: 1.5rem;
}

.form-group {
  margin-bottom: 1rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 500;
  font-size: 0.9rem;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 0.6rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
}

.modal-actions {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  margin-top: 2rem;
}
</style>

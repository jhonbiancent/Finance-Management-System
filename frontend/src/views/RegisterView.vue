<template>
  <div class="register-view">
    <h1>Register</h1>
    <form @submit.prevent="handleRegister">
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <div class="form-group">
        <label for="role">Role:</label>
        <select id="role" v-model="role" required>
          <option value="ACCOUNTS_PAYABLE">Accounts Payable</option>
          <option value="ACCOUNTS_RECEIVABLE">Accounts Receivable</option>
          <option value="COLLECTION">Collection</option>
          <option value="CONTROLLER">Controller</option>
          <option value="DISBURSEMENT">Disbursement</option>
          <option value="TREASURER">Treasurer</option>
          <option value="FINANCE_DIRECTOR">Finance Director (Super Admin)</option>
        </select>
      </div>
      <button type="submit" :disabled="loading">
        {{ loading ? 'Registering...' : 'Register' }}
      </button>
      <p v-if="error" class="error">{{ error }}</p>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';

const username = ref('');
const password = ref('');
const role = ref('ROLE_MEMBER');
const loading = ref(false);
const error = ref(null);

const authStore = useAuthStore();
const router = useRouter();

const handleRegister = async () => {
  loading.value = true;
  error.value = null;
  try {
    await authStore.register({
      username: username.value,
      password: password.value,
      role: role.value,
    });
    router.push('/login');
  } catch (err) {
    error.value = 'Registration failed';
    console.error('Registration failed:', err);
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.register-view {
  max-width: 400px;
  margin: 50px auto;
  padding: 2rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
h1 {
  text-align: center;
  margin-bottom: 1.5rem;
}
.form-group {
  margin-bottom: 1rem;
}
label {
  display: block;
  margin-bottom: 0.5rem;
}
input, select {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}
button {
  width: 100%;
  padding: 0.75rem;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
}
button:disabled {
  background-color: #ccc;
}
.error {
  color: red;
  margin-top: 1rem;
  text-align: center;
}
</style>

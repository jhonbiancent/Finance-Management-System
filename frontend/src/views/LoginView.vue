<template>
  <div class="login-page">
    <div class="logo-section">
      <img :src="qgclogoWide" alt="QGCLogo">
    </div>
    <div class="login-section">
      <div class="login-view">
        <h1>ACCOUNT LOGIN</h1>
        <form @submit.prevent="handleLogin">
          <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" v-model="username" required />
          </div>
          <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" v-model="password" required />
          </div>
          <button type="submit" :disabled="loading">
            {{ loading ? 'Logging in...' : 'Login' }}
          </button>
          <p v-if="error" class="error">{{ error }}</p>
        </form>
        <div class="register-link">
          <span>Don't have an account?</span><router-link to="/register"> Register</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';
import qgclogoWide from '../assets/qgclogo_wide.png';

const username = ref('');
const password = ref('');
const loading = ref(false);
const error = ref(null);

const authStore = useAuthStore();
const router = useRouter();

const handleLogin = async () => {
  loading.value = true;
  error.value = null;
  try {
    await authStore.login({ username: username.value, password: password.value });
    router.push('/dashboard');
  } catch (err) {
    error.value = 'Invalid username or password';
    console.error('Login failed:', err);
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.login-page{
  width:100%;
  height:100vh;
  display:flex;
  background-color:black;
}
.logo-section{
  width:60%;
  display:flex;
  align-items: center;
  justify-content:center;
}
.logo-section img{
  width:80%;
}
.login-section{
  width:40%;
  background-color:white;
  border-top-left-radius: 5rem;
  color:black;
  display:flex;
  align-items:center;
  justify-content:center;
}
.login-view {
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
  color:black;
}
.form-group {
  margin-bottom: 1rem;
}
.form-group input{
  background-color:white;

}
label {
  display: block;
  margin-bottom: 0.5rem;
}
input {
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
.register-link {
  text-align: center;
  margin-top: 1rem;
}
</style>
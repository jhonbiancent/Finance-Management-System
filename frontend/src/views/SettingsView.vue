<script setup>
import { useTheme } from '../composables/useTheme'

const { isDark, toggleTheme } = useTheme()
</script>

<template>
  <div class="view-container">
    <header class="top-bar">
      <h1>Settings</h1>
    </header>
    <div class="content-area">
      <div class="settings-card">
        <h2>User Profile</h2>
        <div class="form-group">
          <label>Display Name</label>
          <input type="text" value="User Name" />
        </div>
        <div class="form-group">
          <label>Email</label>
          <input type="email" value="user@finance.org" />
        </div>
        <button class="btn-primary">Save Profile</button>
      </div>

      <div class="settings-card">
        <h2>Preferences</h2>
        <div class="checkbox-group">
          <input
              type="checkbox"
              id="darkmode"
              :checked="isDark"
              @change="toggleTheme"
          />
          <label for="darkmode">Enable Dark Mode</label>
        </div>
        <div class="checkbox-group">
          <input type="checkbox" id="notifications" checked />
          <label for="notifications">Email Notifications</label>
        </div>
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
  height: 64px; /* Fixed height to match sidebar */
  padding: 0 2rem;
  border-bottom: 1px solid var(--border-color);
  display: flex;
  align-items: center;
}

.top-bar h1 {
  margin: 0;
  font-size: 1.5rem;
}

.content-area {
  padding: 2rem;
  flex: 1;
  overflow-y: auto;
}

.settings-card {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  border: 1px solid var(--border-color);
  margin-bottom: 2rem;
  max-width: 600px;
}

.settings-card h2 {
  margin-top: 0;
  font-size: 1.25rem;
  margin-bottom: 1.5rem;
}

.form-group {
  margin-bottom: 1rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 500;
}

.form-group input {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.checkbox-group {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  margin-bottom: 0.75rem;
}

.btn-primary {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 1rem;
}
</style>
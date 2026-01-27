<script setup>
import { computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { useTheme } from './composables/useTheme'

const route = useRoute()
const { initTheme } = useTheme()

// Helper to determine active class
const isActive = (path) => route.path === path

onMounted(() => {
  initTheme()
})
</script>

<template>
  <div class="layout">
    <!-- Sidebar / Drawer -->
    <aside class="sidebar">
      <div class="brand">
        <h2>FinancePortal</h2>
      </div>
      <nav class="nav-menu">
        <router-link to="/" class="nav-item" :class="{ active: isActive('/') }">
          <span class="icon">📊</span> Dashboard
        </router-link>
        <router-link to="/transactions" class="nav-item" :class="{ active: isActive('/transactions') }">
          <span class="icon">💰</span> Transactions
        </router-link>
        <router-link to="/reports" class="nav-item" :class="{ active: isActive('/reports') }">
          <span class="icon">📈</span> Reports
        </router-link>
        <router-link to="/settings" class="nav-item" :class="{ active: isActive('/settings') }">
          <span class="icon">⚙️</span> Settings
        </router-link>
      </nav>
      <div class="user-profile">
        <div class="avatar">U</div>
        <div class="info">
          <p class="name">User Name</p>
          <p class="role">Finance Staff</p>
        </div>
      </div>
    </aside>

    <!-- Main Content Area -->
    <main class="main-content">
      <router-view />
    </main>
  </div>
</template>

<style scoped>
.layout {
  display: flex;
  height: 100vh;
  width: 100vw;
  overflow: hidden;
}

/* Sidebar */
.sidebar {
  width: 260px;
  background-color: var(--sidebar-bg);
  color: var(--sidebar-text);
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
}

.brand {
  padding: 1.5rem;
  border-bottom: 1px solid rgba(255,255,255,0.1);
}

.brand h2 {
  margin: 0;
  font-size: 1.25rem;
  font-weight: 600;
}

.nav-menu {
  flex: 1;
  padding: 1rem 0;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.nav-item {
  display: flex;
  align-items: center;
  padding: 0.75rem 1.5rem;
  color: rgba(255,255,255,0.7);
  text-decoration: none;
  transition: all 0.2s;
}

.nav-item:hover, .nav-item.active {
  background-color: rgba(255,255,255,0.1);
  color: #fff;
  border-right: 4px solid #3498db;
}

.nav-item .icon {
  margin-right: 0.75rem;
  font-size: 1.2rem;
}

.user-profile {
  padding: 1rem 1.5rem;
  border-top: 1px solid rgba(255,255,255,0.1);
  display: flex;
  align-items: center;
  gap: 1rem;
}

.avatar {
  width: 36px;
  height: 36px;
  background-color: #3498db;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
}

.info p {
  margin: 0;
  font-size: 0.85rem;
}
.info .role {
  color: rgba(255,255,255,0.5);
  font-size: 0.75rem;
}

/* Main Content */
.main-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  background-color: var(--bg-color);
  overflow: hidden;
}
</style>

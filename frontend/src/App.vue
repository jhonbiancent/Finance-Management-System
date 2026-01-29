<script setup>
import { computed, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import { useTheme } from './composables/useTheme'
import { useAuthStore } from './stores/auth';
import qgclogoSmall from './assets/qgclogo_small.png';
import qgclogoWide from './assets/qgclogo_wide.png';

const route = useRoute()
const { initTheme } = useTheme()
const authStore = useAuthStore()

const isLoginPage = computed(() => route.name === 'Login' || route.path === '/login')
const user = computed(() => authStore.user)
const isSidebarCollapsed = ref(false)

const toggleSidebar = () => {
  isSidebarCollapsed.value = !isSidebarCollapsed.value
}

onMounted(() => {
  initTheme()
})
</script>

<template>
  <div class="app-container">
    <div v-if="isLoginPage" class="full-screen">
      <router-view />
    </div>

    <div v-else class="layout">
      <aside
          class="sidebar"
          :class="{ 'is-collapsed': isSidebarCollapsed }"
      >

        <div
            class="brand interactive-area"
            @click="toggleSidebar"
            title="Click to toggle sidebar"
        >
          <div class="logo-container">
            <img v-if="!isSidebarCollapsed" :src="qgclogoWide" alt="Logo" class="wide-logo" />
            <img v-else :src="qgclogoSmall" alt="Logo" class="collapsed-logo" />
          </div>
        </div>

        <nav class="nav-menu">
          <router-link to="/" class="nav-item">
            <span class="icon"><i class="fas fa-chart-pie"></i></span>
            <span v-if="!isSidebarCollapsed" class="nav-text">Dashboard</span>
          </router-link>

          <router-link to="/transactions" class="nav-item">
            <span class="icon"><i class="fas fa-money-bill-wave"></i></span>
            <span v-if="!isSidebarCollapsed" class="nav-text">Transactions</span>
          </router-link>

          <router-link to="/reports" class="nav-item">
            <span class="icon"><i class="fas fa-chart-line"></i></span>
            <span v-if="!isSidebarCollapsed" class="nav-text">Reports</span>
          </router-link>

          <router-link to="/settings" class="nav-item">
            <span class="icon"><i class="fas fa-cog"></i></span>
            <span v-if="!isSidebarCollapsed" class="nav-text">Settings</span>
          </router-link>
        </nav>

        <div
            class="user-profile interactive-area"
            @click="toggleSidebar"
            title="Click to toggle sidebar"
        >
          <div class="avatar">{{ user?.sub?.charAt(0).toUpperCase() || 'U' }}</div>

          <div class="info" v-if="!isSidebarCollapsed">
            <p class="name">{{ user?.sub || 'User' }}</p>
            <p class="role">{{ user?.roles?.[0] || 'Member' }}</p>
          </div>

          <button
              v-if="!isSidebarCollapsed"
              @click.stop="authStore.logout()"
              class="logout-btn"
              title="Logout"
          >
            <i class="fas fa-sign-out-alt"></i>
          </button>
        </div>
      </aside>

      <main class="main-content">
        <router-view />
      </main>
    </div>
  </div>
</template>

<style scoped>
.app-container {
  display: flex;
  height: 100vh;
  width: 100vw;
  overflow: hidden;
}

.full-screen {
  width: 100%;
  height: 100%;
}

.layout {
  display: flex;
  width: 100%;
  height: 100%;
}

.sidebar {
  width: 260px;
  background-color: var(--sidebar-bg, #2c3e50);
  color: var(--sidebar-text, #ffffff);
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
  transition: width 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  overflow-x: hidden;
  cursor: default;
}

.sidebar.is-collapsed {
  width: 80px;
}

/* --- Interactive Areas (Logo & Profile) --- */
.interactive-area {
  cursor: pointer;
  transition: background-color 0.2s;
}

.interactive-area:hover {
  background-color: rgba(255, 255, 255, 0.05);
}

/* --- Brand Section --- */
.brand {
  height: 64px;
  /* CHANGED: Increased left/right padding to 1.5rem to match .nav-item padding */
  padding: 0 1.5rem;
  border-bottom: 1px solid rgba(255,255,255,0.1);
  display: flex;
  align-items: center;
  overflow: hidden;
}

.logo-container {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  height: 100%;
  width: 100%;
  pointer-events: none;
}

/* When collapsed, force Center Align so the small logo looks right */
.sidebar.is-collapsed .logo-container {
  justify-content: center;
}

.wide-logo {
  height: 40px;
  width: auto;
  animation: fadeIn 0.3s ease;
  margin-left: 0;
}

.collapsed-logo {
  height: 32px;
  width: 32px;
  object-fit: contain;
  animation: fadeIn 0.3s ease;
}

@keyframes fadeIn {
  from { opacity: 0; transform: scale(0.9); }
  to { opacity: 1; transform: scale(1); }
}

/* --- Navigation --- */
.nav-menu {
  flex: 1;
  padding: 1rem 0;
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
  overflow-y: auto;
  overflow-x: hidden;
}

.nav-menu::-webkit-scrollbar {
  width: 4px;
}
.nav-menu::-webkit-scrollbar-thumb {
  background: rgba(255, 255, 255, 0.2);
  border-radius: 4px;
}
.nav-menu::-webkit-scrollbar-track {
  background: transparent;
}

.nav-item {
  display: flex;
  align-items: center;
  /* Note: 1.5rem here is what we matched in the .brand class above */
  padding: 0.75rem 1.5rem;
  color: rgba(255,255,255,0.7);
  text-decoration: none;
  transition: all 0.2s;
  border-left: 4px solid transparent;
  white-space: nowrap;
  position: relative;
  z-index: 10;
  cursor: pointer;
}

.nav-item:hover,
.router-link-active {
  background-color: rgba(255,255,255,0.1);
  color: #fff;
  border-left: 4px solid #3498db;
}

.nav-item .icon {
  width: 24px;
  text-align: center;
  font-size: 1.2rem;
  margin-right: 0;
  flex-shrink: 0;
}

.nav-text {
  margin-left: 0.75rem;
}

.sidebar.is-collapsed .nav-item {
  justify-content: center;
  padding: 0.75rem 0;
}

.sidebar.is-collapsed .nav-text {
  display: none;
}

/* --- User Profile --- */
.user-profile {
  padding: 1rem;
  border-top: 1px solid rgba(255,255,255,0.1);
  display: flex;
  align-items: center;
  gap: 0.75rem;
  height: 70px;
  overflow: hidden;
}

.sidebar.is-collapsed .user-profile {
  justify-content: center;
  padding: 1rem 0;
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
  flex-shrink: 0;
  pointer-events: none;
}

.info {
  flex: 1;
  overflow: hidden;
  min-width: 0;
  pointer-events: none;
}

.info p {
  margin: 0;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.info .name {
  font-size: 0.85rem;
  font-weight: 600;
}

.info .role {
  color: rgba(255,255,255,0.5);
  font-size: 0.75rem;
}

.logout-btn {
  background: none;
  border: none;
  color: var(--sidebar-text, #fff);
  cursor: pointer;
  font-size: 1.1rem;
  opacity: 0.7;
  transition: opacity 0.2s;
  padding: 0.5rem;
  flex-shrink: 0;
  pointer-events: auto;
}

.logout-btn:hover {
  opacity: 1;
  color: #e74c3c;
}

.main-content {
  flex: 1;
  background-color: var(--bg-color, #f4f7f6);
  overflow-y: auto;
  position: relative;
}
</style>
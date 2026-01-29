<template>
  <div class="view-container">
    <header class="top-bar">
      <h1>Dashboard</h1>
    </header>
    <div class="content-area">
      <div class="controls">
        <select v-model="selectedSubsidiary" @change="fetchDashboardData">
          <option value="">All Subsidiaries</option>
          <option v-for="sub in subsidiaries" :key="sub.id" :value="sub.id">{{ sub.name }}</option>
        </select>
      </div>
      
      <div class="cards">
        <div class="card">
          <h3>Requests</h3>
          <p class="value">{{ summary.requests }}</p>
        </div>
        <div class="card">
          <h3>Approved Items</h3>
          <p class="value">{{ summary.approvedItems }}</p>
        </div>
        <div class="card">
          <h3>Rejected Items</h3>
          <p class="value">{{ summary.rejectedItems }}</p>
        </div>
      </div>
      
      <div class="chart-container">
        <Bar v-if="chartData.datasets.length" :data="chartData" :options="chartOptions" />
        <div v-else class="placeholder-chart">
          <p>📊 Loading Chart Data...</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { Bar } from 'vue-chartjs';
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js';
import apiClient from '@/api';

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

const selectedSubsidiary = ref('');
const subsidiaries = ref([]);
const summary = reactive({
  requests: 0,
  approvedItems: 0,
  rejectedItems: 0,
});

const chartData = reactive({
  labels: [],
  datasets: []
});

const chartOptions = {
  responsive: true,
  maintainAspectRatio: false,
  scales: {
    y: {
      beginAtZero: true
    }
  }
};

const fetchDashboardData = async () => {
  try {
    // Fetch summary data
    const summaryRes = await apiClient.get('/transactions/summary', { params: { subsidiaryId: selectedSubsidiary.value } });
    Object.assign(summary, summaryRes.data);
    summary.requests = 42;


    // Fetch chart data
    const chartRes = await apiClient.get('/transactions/expenses-by-subsidiary', { params: { subsidiaryId: selectedSubsidiary.value } });
    chartData.labels = chartRes.data.labels;
    chartData.datasets = chartRes.data.datasets;
    
  } catch (error) {
    console.error('Error fetching dashboard data:', error);
  }
};

const fetchSubsidiaries = async () => {
  try {
    const response = await apiClient.get('/subsidiaries');
    subsidiaries.value = response.data;
  } catch (error) {
    console.error('Error fetching subsidiaries:', error);
  }
};

const formatCurrency = (value) => {
  return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(value);
};

onMounted(() => {
  fetchSubsidiaries();
  fetchDashboardData();
});
</script>

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
}

.controls {
  margin-bottom: 1.5rem;
}

.controls select {
  padding: 0.5rem 1rem;
  border-radius: 6px;
  border: 1px solid var(--border-color);
}

.content-area {
  padding: 2rem;
  flex: 1;
  overflow-y: auto;
}

.cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 1.5rem;
  margin-bottom: 2rem;
}

.card {
  background: white;
  padding: 1.5rem;
  border-radius: 8px;
  border: 1px solid var(--border-color);
  box-shadow: 0 2px 4px rgba(0,0,0,0.05);
}

.card h3 {
  margin: 0 0 0.5rem 0;
  color: #666;
  font-size: 0.9rem;
  font-weight: 500;
}

.card .value {
  margin: 0;
  font-size: 1.8rem;
  font-weight: 700;
}

.value.income { color: #27ae60; }
.value.expense { color: #c0392b; }

.chart-container {
  background: white;
  padding: 1.5rem;
  border-radius: 8px;
  border: 1px solid var(--border-color);
  height: 400px;
}

.placeholder-chart {
  background: white;
  border: 1px dashed #ccc;
  border-radius: 8px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #888;
  font-size: 1.2rem;
}
</style>

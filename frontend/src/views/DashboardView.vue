<template>
  <div class="view-container">
    <header class="top-bar">
      <h1>Dashboard</h1>
    </header>
    <div class="content-area">
      <div class="cards">
        <div class="card">
          <h3>Total Balance</h3>
          <p class="value">$12,450.00</p>
        </div>
        <div class="card">
          <h3>Income (Month)</h3>
          <p class="value income">+$5,200.00</p>
        </div>
        <div class="card">
          <h3>Expenses (Month)</h3>
          <p class="value expense">-$1,850.00</p>
        </div>
      </div>

      <div class="placeholder-chart">
        <p>📊 Chart Visualization Placeholder</p>
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
  margin: 0; /* Remove default margin for perfect centering */
  font-size: 1.5rem;
}

.content-area {
  padding: 2rem;
  flex: 1;
  overflow-y: auto;
}

.cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
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

.placeholder-chart {
  background: white;
  border: 1px dashed #ccc;
  border-radius: 8px;
  height: 300px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #888;
  font-size: 1.2rem;
}
</style>
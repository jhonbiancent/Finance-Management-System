<template>
  <div class="view-container">
    <header class="top-bar">
      <h1>Reports</h1>
      <button class="btn-secondary">Download PDF</button>
    </header>
    <div class="content-area">
      <div class="report-section">
        <h2>Financial Summary</h2>
        <div class="chart-container">
          <p>📈 Line Chart: Income vs Expenses (Year to Date)</p>
        </div>
      </div>

      <div class="report-section">
        <h2>Category Breakdown</h2>
        <div class="chart-container">
          <p>🍩 Doughnut Chart: Expense Categories</p>
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
  padding: 1rem 2rem;
  border-bottom: 1px solid var(--border-color);
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.content-area {
  padding: 2rem;
  flex: 1;
  overflow-y: auto;
}

.report-section {
  background: white;
  padding: 1.5rem;
  border-radius: 8px;
  border: 1px solid var(--border-color);
  margin-bottom: 2rem;
}

.report-section h2 {
  margin-top: 0;
  font-size: 1.2rem;
  border-bottom: 1px solid #eee;
  padding-bottom: 1rem;
  margin-bottom: 1rem;
}

.chart-container {
  height: 200px;
  background: #f8f9fa;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
  border-radius: 4px;
}

.btn-secondary {
  background-color: white;
  border: 1px solid #ccc;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
}
</style>

window.onload = function () {
    const incomeTab = document.getElementById('income-tab');
    const expenseTab = document.getElementById('expense-tab');
    const incomeForm = document.getElementById('income-form');
    const expenseForm = document.getElementById('expense-form');

    // URLのtabパラメータを取得
    const urlParams = new URLSearchParams(window.location.search);
    const selectedTab = urlParams.get('tab');

    if (selectedTab === 'expense') {
        // 支出タブを表示
        expenseTab.classList.add('active');
        incomeTab.classList.remove('active');
        expenseForm.style.display = 'block';
        incomeForm.style.display = 'none';
    } else {
        // 収入タブを表示（デフォルト）
        incomeTab.classList.add('active');
        expenseTab.classList.remove('active');
        incomeForm.style.display = 'block';
        expenseForm.style.display = 'none';
    }

    // タブクリックで切り替え
    incomeTab.addEventListener('click', () => {
        incomeTab.classList.add('active');
        expenseTab.classList.remove('active');
        incomeForm.style.display = 'block';
        expenseForm.style.display = 'none';
    });

    expenseTab.addEventListener('click', () => {
        expenseTab.classList.add('active');
        incomeTab.classList.remove('active');
        incomeForm.style.display = 'none';
        expenseForm.style.display = 'block';
    });

    // 削除ポップアップ関連
    const deleteIcons = document.querySelectorAll('.delete-icon');
    const incomePopup = document.getElementById('income-delete-popup');
    const expensePopup = document.getElementById('expense-delete-popup');
    const cancelDeleteIncome = document.getElementById('cancel-delete');
    const cancelDeleteExpense = document.getElementById('cancel-delete-expense');

    const deleteIncomeIdInput = document.getElementById('delete-income-id');
    const deleteExpenseIdInput = document.getElementById('delete-expense-id');

    let targetRow = null;

    deleteIcons.forEach(icon => {
        icon.addEventListener('click', (e) => {
            const id = e.target.getAttribute('data-id');
            // どのタブが表示されているか判定
            const isIncomeTab = incomeForm.style.display !== 'none';

            targetRow = e.target.closest('tr');

            if (isIncomeTab) {
                deleteIncomeIdInput.value = id;
                incomePopup.classList.remove('hidden');
            } else {
                deleteExpenseIdInput.value = id;
                expensePopup.classList.remove('hidden');
            }
        });
    });

    cancelDeleteIncome.addEventListener('click', () => {
        incomePopup.classList.add('hidden');
        targetRow = null;
    });

    cancelDeleteExpense.addEventListener('click', () => {
        expensePopup.classList.add('hidden');
        targetRow = null;
    });
};

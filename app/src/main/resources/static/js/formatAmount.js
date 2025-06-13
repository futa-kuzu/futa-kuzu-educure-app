function formatWithCommas(value) {
  return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

// 金額表示にカンマをつける処理
function setupAmountFormatter(formId, inputId) {
  window.addEventListener("DOMContentLoaded", function () {
    const amountInput = document.getElementById(inputId);
    if (amountInput) {
      const rawValue = amountInput.value.replace(/,/g, '');
      if (!isNaN(rawValue) && rawValue !== '') {
        amountInput.value = formatWithCommas(rawValue);
      }
    }
  });

  document.getElementById(formId)?.addEventListener("submit", function () {
    const amountInput = document.getElementById(inputId);
    if (amountInput) {
      amountInput.value = amountInput.value.replace(/,/g, '');
    }
  });
}

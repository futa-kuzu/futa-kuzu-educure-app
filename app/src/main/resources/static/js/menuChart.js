document.addEventListener('DOMContentLoaded', () => {
    const fg = document.querySelector('.circle-fg');
    const progressText = document.querySelector('.percentage-text');

    if (!fg || !progressText) return;

    let progress = parseFloat(progressText.textContent) || 0;
    progress = Math.max(progress, 0); // 最低0%

    const radius = 110;
    const circumference = 2 * Math.PI * radius;
    const offset = circumference * (1 - progress / 100);

    fg.style.strokeDasharray = circumference;
    fg.style.strokeDashoffset = offset;
});

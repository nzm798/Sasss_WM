function toggleCheckbox(checkbox) {
    const checkboxes = document.getElementsByName("user");
    checkboxes.forEach(cb => {
        if (cb !== checkbox) {
            cb.checked = false; // 取消选中其他复选框
        }
    });
}
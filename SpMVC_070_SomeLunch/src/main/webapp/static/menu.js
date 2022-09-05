document.addEventListener("DOMContentLoaded", () => {
  const lunch_list = document.querySelector("table.LUNCHS");
  lunch_list?.addEventListener("click", (e) => {
    console.log("테이블 클릭했음");
    const td = e.target;
    if (td.tagName === "TD") {
      const tr = td.closest("TR");
      const MLSV_YMD = tr.dataset.mlsv_ymd;
      document.location.href = `${rootPath}/lunch/${MLSV_YMD}/menu`;
    }
  });
});

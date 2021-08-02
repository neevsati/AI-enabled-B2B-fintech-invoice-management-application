const predictBtn = document.querySelector(".predict-btn");
const addBtn = document.querySelector(".add-btn");
const editBtn = document.querySelector(".edit-btn");

const overlay = document.querySelector("#overlay");

const modals = document.querySelectorAll(".modal");
const modalCloseBtns = document.querySelectorAll(".modal-close-btn");
const modalCancelBtns = document.querySelectorAll(".modal-cancel-btn");
const addFunctionBtn = document.querySelector(".add-post-btn");

addBtn.addEventListener("click", () => {
  overlay.style.display = "flex";
  modals[0].style.display = "block";
  modals[1].style.display = "none";
});

editBtn.addEventListener("click", () => {
  overlay.style.display = "flex";
  modals[0].style.display = "none";
  modals[1].style.display = "block";
});

overlay.addEventListener("click", (e) => {
  if (e.target.contains(overlay)) {
    overlay.style.display = "none";
    modals[0].style.display = "none";
    modals[1].style.display = "none";
  }
});

modalCloseBtns.forEach((modalCloseBtn, index) => {
  modalCloseBtn.addEventListener("click", () => {
    overlay.style.display = "none";
    modals[index].style.display = "none";
  });
});

modalCancelBtns.forEach((modalCancelBtn, index) => {
  modalCancelBtn.addEventListener("click", () => {
    overlay.style.display = "none";
    modals[index].style.display = "none";
  });
});

document.addEventListener('DOMContentLoaded', function() {
  const form = document.getElementById('contactForm');
  const successMessage = document.getElementById('successMessage');

  if (form) {
    form.addEventListener('submit', async function(e) {
      e.preventDefault();

      const submitBtn = form.querySelector('button[type="submit"]');
      submitBtn.disabled = true;
      submitBtn.textContent = 'Sending...';

      try {
        const response = await fetch(form.action, {
          method: 'POST',
          body: new FormData(form),
          headers: { 'Accept': 'application/json' }
        });

        if (response.ok) {
          form.style.display = 'none';
          successMessage.style.display = 'block';
        } else {
          throw new Error('Failed to send');
        }
      } catch (error) {
        submitBtn.disabled = false;
        submitBtn.textContent = 'Send';
        alert('Something went wrong. Please try again.');
      }
    });
  }
});

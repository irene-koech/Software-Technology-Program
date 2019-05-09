const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

const sgMail = require('@sendgrid/mail')
sgMail.setApiKey('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');

exports.orderMail = functions.firestore.document('/Users/{userId}/orders/{orderId}').onCreate(event => {
  //console.log(snapshot);
    
  const msg = {
    to: 'jr222wb@student.lnu.se',
    from: 'order@mobileincase.com',
    subject: 'Order confirmation',
    // text: `Hey ${toName}. You have a new follower!!! `,
    // html: `<strong>Hey ${toName}. You have a new follower!!!</strong>`,

    // custom templates
    templateId: 'c5411552-f258-4f30-86d0-4b0ca6a70944',
    substitutionWrappers: ['{{', '}}'],
    substitutions: {
      name: event
      // and other custom properties here
    }
  };
  return sgMail.send(msg);
});


<?php

namespace AchatBundle\Controller;

use AchatBundle\Entity\Commande;
use AchatBundle\Entity\LigneCommande;
use AchatBundle\Entity\Panier;
use Cassandra\Date;
use DateTime;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
//use Symfony\Component\BrowserKit\Response;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Security\Core\Exception\AccessDeniedException;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 * ApiCommande controller.
 *
 * @Route("Apicommande")
 */
class ApiCommandeController extends Controller
{
    /**
     * Lists all commande entities.
     *
     * @Route("/index", name="api_commande_index")
     * @Method("GET")
     */
    public function indexAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();

//        $commandes = $em->getRepository('AchatBundle:Commande')->findBy();
        $user = $em->getRepository("Proxies\\__CG__\\UserBundle\\Entity\\User")->find($request->get('user'));

        $commandes=$em->getRepository('AchatBundle:Commande')->findByUser_Id($user);


        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($commandes);
        return new JsonResponse($formatted);
    }





    /**
     * Creates a new commande entity.
     *
     * @Route("/new", name="api_commande_new")
     * @Method({"GET","POST"})
     */
    public function newAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
//        $panier=$em->getRepository("AchatBundle:Panier")->find($request->get('id'));
        $user = $em->getRepository("Proxies\\__CG__\\UserBundle\\Entity\\User")->find($request->get('user'));
        $panier=$em->getRepository("AchatBundle:Panier")->findByUser_Id($user);
//        dump($user,$panier);
//        exit();


        $commande = new Commande();
            $commande->setUserId($user);
            $commande->setIdPanier($panier);
            $commande->setDate(new DateTime('now'));
            $commande->setEtat(false);
            $ad = $request->get('adress');
            $commande->setAddress($ad);
            $tel = $request->get('tel');
            $commande->setTel($tel);
            $em->persist($commande);
//            $em->flush();


        $panier->setEtat(false);
        $em->persist($panier);
//        $em->flush();
        $panier1=new Panier();
        $panier1->setUserId($user);
        $panier1->setPrixtotal(0);
        $panier1->setEtat(true);
        $em->persist($panier1);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($commande);
        return new JsonResponse($formatted);

    }


    /**
     * Finds and displays a commande entity.
     *
     * @Route("/{id}/show", name="api_commande_show")
     * @Method("GET")
     */


    public function showAction(Request $request)
    {
//        $deleteForm = $this->createDeleteForm($commande);
        $em = $this->getDoctrine()->getManager();

        $commande = $this->getDoctrine()->getManager()->getRepository('AchatBundle:Commande')->find($request->get('id'));
//        $panier=$em->getRepository("AchatBundle:Panier")->find($commande->getIdPanier());
        $ligneCommandes = $em->getRepository('AchatBundle:LigneCommande')->findByPanier($commande->getIdPanier());
//        dump($ligneCommandes);
//        exit();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize([$commande,$ligneCommandes]);
        return new JsonResponse($formatted);

    }


    /**
     * Finds and displays a commande entity.
     *
     * @Route("/{id}/show", name="api_commande_show2")
     * @Method("GET")
     */
    public function show2Action(Commande $commande)
    {
        $ligneCommandes = $this->getDoctrine()->getManager()->getRepository('AchatBundle:LigneCommande')->findByPanier($commande->getIdPanier());

        return $this->render('@Achat/commande/show2.html.twig', array(
            'commande' => $commande,
            'ligneCommandes' => $ligneCommandes,
        ));
    }

    /**
     * Displays a form to edit an existing commande entity.
     *
     * @Route("/{id}/edit", name="api_commande_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Commande $commande)
    {
        $deleteForm = $this->createDeleteForm($commande);
        $editForm = $this->createForm('AchatBundle\Form\CommandeType', $commande);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('commande_edit', array('id' => $commande->getId()));
        }

        return $this->render('@Achat/commande/edit.html.twig', array(
            'commande' => $commande,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a commande entity.
     *
     * @Route("/{id}/delete", name="api_commande_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, Commande $commande)
    {
        $form = $this->createDeleteForm($commande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($commande);
            $em->flush();
        }

        return $this->redirectToRoute('commande_index');
    }

    /**
     * Creates a form to delete a commande entity.
     *
     * @param Commande $commande The commande entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Commande $commande)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('commande_delete', array('id' => $commande->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }

    /**
     * print commande
     *
     * @Route("/{id}/print", name="commande_print")
     * @Method({"GET", "POST"})
     */

    public function printAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $commande = $this->getDoctrine()->getManager()->getRepository('AchatBundle:Commande')->find($id);
        $panier=$em->getRepository("AchatBundle:Panier")->find($commande->getIdPanier());
        $ligneCommandes = $em->getRepository('AchatBundle:LigneCommande')->findByPanier($panier->getId());


        $snappy = $this->get('knp_snappy.pdf');

        $html = $this->render('@Achat/commande/print.html.twig', array(
            'user' => $this->getUser(),
            'ligneCommandes'  => $ligneCommandes,
            'commande' => $commande
        ));

        $filename = "commande";
        return new Response(
            $snappy->getOutputFromHtml($html),
            200,
            array(
                'content-Type' => 'app/pdf',
                'content-Disposition' => 'inline; filename="'.$filename.'.pdf"'
            )
        );
    }



    /**
     * payer panier .
     *
     * @Route("/{id}/payer", name="api_commande_payer")
     * @Method("GET")
     */

    public function payerAction($id)
    {




            $em = $this->getDoctrine()->getManager();
//        $panier = $em->getRepository(Panier::class)->findByUser_Id($this->getUser());
            $panier = $em->getRepository(Panier::class)->find($id);

//dump($panier);
//exit();

            \Stripe\Stripe::setApiKey("sk_test_lE9pTHIXFMFcZr7CZvTS33wM00fIb8c2WL");

            \Stripe\Charge::create(array(
                "amount" => $panier->getPrixtotal()*100,
                "currency" => "usd",
                "source" => 'tok_mastercard', // obtained with Stripe.js
                "description" => $this->getUser()->getUsername(),

            ));

            return $this->render('@Achat/commande/payement.html.twig');


//        return $this->render('@Achat/commande/index.html.twig');

    }


}

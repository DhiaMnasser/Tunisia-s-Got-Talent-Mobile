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
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Security\Core\Exception\AccessDeniedException;

/**
 * Commande controller.
 *
 * @Route("commande")
 */
class CommandeController extends Controller
{
    /**
     * Lists all commande entities.
     *
     * @Route("/", name="commande_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        if (!($this->get('security.authorization_checker')->isGranted('ROLE_USER')))
        {

            throw new AccessDeniedException('accés user');
        }
        $em = $this->getDoctrine()->getManager();

//        $commandes = $em->getRepository('AchatBundle:Commande')->findBy();
        $commandes=$em->getRepository('AchatBundle:Commande')->findByUser_Id($this->getUser());

        return $this->render('@Achat/commande/index.html.twig', array(
            'commandes' => $commandes,
        ));
    }

    /**
     * Lists all commande entities.
     *
     * @Route("/admin", name="commande_index2")
     * @Method("GET")
     */
    public function index2Action()
    {
        if (($this->get('security.authorization_checker')->isGranted('ROLE_ADMIN')))
        {

            throw new AccessDeniedException('accés admin');
        }

        $em = $this->getDoctrine()->getManager();

        $commandes = $em->getRepository('AchatBundle:Commande')->findAll();

        return $this->render('@Achat/commande/index2.html.twig', array(
            'commandes' => $commandes,
        ));
    }


    /**
     * Lists all commande entities.
     *
     * @Route("/admin/traitee", name="commande_index3")
     * @Method("GET")
     */
    public function index3Action()
    {
        $em = $this->getDoctrine()->getManager();

        $commandes = $em->getRepository('AchatBundle:Commande')->findAll();

        return $this->render('@Achat/commande/index3.html.twig', array(
            'commandes' => $commandes,
        ));
    }


    /**
     * Lists all commande entities.
     *
     * @Route("/admin/encours", name="commande_index4")
     * @Method("GET")
     */
    public function index4Action()
    {
        $em = $this->getDoctrine()->getManager();

        $commandes = $em->getRepository('AchatBundle:Commande')->findAll();

        return $this->render('@Achat/commande/index4.html.twig', array(
            'commandes' => $commandes,
        ));
    }

    /**
     * Creates a new commande entity.
     *
     * @Route("/{id}/new", name="commande_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $panier=$em->getRepository("AchatBundle:Panier")->find($id);

        $ligneCommandes = $em->getRepository('AchatBundle:LigneCommande')->findByPanier($panier->getId());

        $commande = new Commande();

        $form = $this->createForm('AchatBundle\Form\CommandeType', $commande);
        $form->handleRequest($request);
//        var_dump($form);
//        exit();

        if ($form->isSubmitted() && $form->isValid()) {

            if($commande->getTel()<11111111 or $commande->getTel()>99999999){
                return $this->render('@Achat/commande/new.html.twig', array(
                    'commande' => $commande,
                    'form' => $form->createView(),
                ));
            }else{

            $commande->setUserId($this->getUser());
            $commande->setIdPanier($panier);
            $commande->setDate(new \DateTime('now'));
            $commande->setEtat(false);


            $em->persist($commande);
            $em->flush();
            $panierCtrl = $this->get('panier_services');
            $panierCtrl->changerAction($panier,$this);
//            $this->redirectToRoute('panier_changer', array('id' => $panier->getId()));
            return $this->redirectToRoute('commande_payer',array('id' => $panier->getId()));
             }
        }

        return $this->render('@Achat/commande/new.html.twig', array(
            'commande' => $commande,
            'form' => $form->createView(),
        ));

    }



    /**
     * Finds and displays a commande entity.
     *
     * @Route("/{id}", name="commande_show")
     * @Method("GET")
     */
    public function showAction(Commande $commande)
    {
//        $deleteForm = $this->createDeleteForm($commande);
        $em = $this->getDoctrine()->getManager();

//        $commande = $this->getDoctrine()->getManager()->getRepository('AchatBundle:Commande')->find($commande);
//        $panier=$em->getRepository("AchatBundle:Panier")->find($commande->getIdPanier());
        $ligneCommandes = $em->getRepository('AchatBundle:LigneCommande')->findByPanier($commande->getIdPanier());
//        dump($ligneCommandes);
//        exit();
        return $this->render('@Achat/commande/show.html.twig', array(
            'commande' => $commande,
            'ligneCommandes' => $ligneCommandes,
        ));
    }


    /**
     * Finds and displays a commande entity.
     *
     * @Route("/{id}/show", name="commande_show2")
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
     * @Route("/{id}/edit", name="commande_edit")
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
     * @Route("/{id}/delete", name="commande_delete")
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
     * valider une commande entities.
     *
     * @Route("/{id}/valider", name="commande_valider")
     * @Method("GET")
     */
    public function validerAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $commande = $em->getRepository('AchatBundle:Commande')->find($id);
        $commande->setEtat(true);
        $this->getDoctrine()->getManager()->flush();
        return $this->redirectToRoute('commande_index2');

    }


    /**
     * payer panier .
     *
     * @Route("/{id}/payer", name="commande_payer")
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
